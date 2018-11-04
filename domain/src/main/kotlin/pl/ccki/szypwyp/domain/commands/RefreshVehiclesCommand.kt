package pl.ccki.szypwyp.domain.commands

import io.reactivex.Completable
import io.reactivex.Single
import pl.ccki.szypwyp.domain.base.Command
import pl.ccki.szypwyp.domain.base.InjectableMap
import pl.ccki.szypwyp.domain.base.SchedulersProvider
import pl.ccki.szypwyp.domain.base.applySchedulers
import pl.ccki.szypwyp.domain.models.CityId
import pl.ccki.szypwyp.domain.models.DEFAULT_LOCATION
import pl.ccki.szypwyp.domain.models.LatLng
import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.domain.models.PluginId
import pl.ccki.szypwyp.domain.models.compareTo
import pl.ccki.szypwyp.domain.models.distanceTo
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
    private val schedulersProvider: SchedulersProvider
) : Command<Unit> {

    override fun execute(param: Unit): Completable =
        Single.fromCallable {
            val target = searchConfig.target ?: DEFAULT_LOCATION
            val selectedPlugins = findServices(target)

            selectedPlugins.map { (id, cityId, plugin) ->
                Single.fromCallable {
                    RequestDto(id, plugin.findInLocation(cityId))
                }
            }
        }
            .flatMapCompletable {
                Single.mergeDelayError(it)
                    .flatMapCompletable { (id, result) ->
                        persistence.update(id, result)
                    }
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
}

private data class RequestDto(val id: PluginId, val result: List<MarkerModel>)
