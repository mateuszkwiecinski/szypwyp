package pl.ccki.szypwyp.domain.commands

import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import pl.ccki.szypwyp.domain.base.Command
import pl.ccki.szypwyp.domain.base.InjectableMap
import pl.ccki.szypwyp.domain.base.SchedulersProvider
import pl.ccki.szypwyp.domain.base.applySchedulers
import pl.ccki.szypwyp.domain.models.CityId
import pl.ccki.szypwyp.domain.models.DEFAULT_LOCATION
import pl.ccki.szypwyp.domain.models.LatLng
import pl.ccki.szypwyp.domain.models.LoadEvent
import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.domain.models.PluginId
import pl.ccki.szypwyp.domain.models.compareTo
import pl.ccki.szypwyp.domain.models.distanceTo
import pl.ccki.szypwyp.domain.persistences.FiltersPersistence
import pl.ccki.szypwyp.domain.persistences.MapEventsPersistence
import pl.ccki.szypwyp.domain.persistences.VehiclesPersistence
import pl.ccki.szypwyp.domain.repositories.SearchConfigRepository
import pl.ccki.szypwyp.domain.services.ExternalPlugin
import javax.inject.Inject

class RefreshVehiclesCommand @Inject constructor(
    private val filters: FiltersPersistence,
    private val registeredPlugins: InjectableMap<PluginId, ExternalPlugin>,
    private val persistence: VehiclesPersistence,
    private val searchConfig: SearchConfigRepository,
    private val mapEvents: MapEventsPersistence,
    private val schedulersProvider: SchedulersProvider
) : Command<Unit> {

    override fun execute(param: Unit): Completable =
        Single.fromCallable {
            mapEvents.update(LoadEvent.Initial)
            val target = searchConfig.target ?: DEFAULT_LOCATION
            val selectedPlugins = findServices(target)

            selectedPlugins.map { (id, cityId, plugin) ->
                serviceCall(id, cityId, plugin)
            }
        }
            .flatMapCompletable(Completable::merge)
            .doFinally {
                mapEvents.update(LoadEvent.Completed)
            }
            .applySchedulers(schedulersProvider)

    private fun findServices(target: LatLng): List<Triple<PluginId, List<CityId>, ExternalPlugin>> =
        findUserServices().mapNotNull { (id, plugin) ->
            val cities = plugin.supportedCities.filter {
                it.center.distanceTo(target) < it.radius
            }
                .map { it.id }
                .takeIf { it.isNotEmpty() } ?: return@mapNotNull null

            Triple(id, cities, plugin)
        }

    private fun findUserServices(): Map<PluginId, ExternalPlugin> {
        val disabled = filters.current()
            .toSingle(emptyList())
            .blockingGet()

        return (registeredPlugins - disabled).takeIf { it.isNotEmpty() } ?: registeredPlugins
    }

    private fun serviceCall(id: PluginId, cities: List<CityId>, plugin: ExternalPlugin) =
        Maybe.fromCallable<List<MarkerModel>> {
            try {
                mapEvents.update(LoadEvent.Loading(id))
                val result = plugin.findInLocation(cities)
                mapEvents.update(LoadEvent.Finished.WithSuccess(id))
                result
            } catch (throwable: Throwable) {
                mapEvents.update(LoadEvent.Finished.WithError(id, throwable))
                null
            }
        }
            .flatMapCompletable {
                persistence.update(id, it)
            }
            .subscribeOn(schedulersProvider.worker)
}
