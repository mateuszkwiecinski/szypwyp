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
import pl.ccki.szypwyp.domain.persistences.MapEventsPersistence
import pl.ccki.szypwyp.domain.persistences.VehiclesPersistence
import pl.ccki.szypwyp.domain.repositories.SearchConfigRepository
import pl.ccki.szypwyp.domain.repositories.ServicesConfigurationRepository
import pl.ccki.szypwyp.domain.services.ExternalPlugin
import javax.inject.Inject

class RefreshVehiclesCommand @Inject constructor(
    private val configuration: ServicesConfigurationRepository,
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

    private fun findServices(target: LatLng): List<Triple<PluginId, CityId, ExternalPlugin>> =
        findUserServices().mapNotNull { (id, plugin) ->
            val city = plugin.supportedCities.firstOrNull {
                it.center.distanceTo(target) < it.radius
            } ?: return@mapNotNull null

            Triple(id, city.id, plugin)
        }

    private fun findUserServices(): Map<PluginId, ExternalPlugin> {
        val services = configuration.selected ?: return registeredPlugins

        return registeredPlugins.filterKeys {
            services.contains(it)
        }
    }

    private fun serviceCall(id: PluginId, cityId: CityId, plugin: ExternalPlugin) =
        Maybe.fromCallable<List<MarkerModel>> {
            try {
                mapEvents.update(LoadEvent.Loading(id))
                val result = plugin.findInLocation(cityId)
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
