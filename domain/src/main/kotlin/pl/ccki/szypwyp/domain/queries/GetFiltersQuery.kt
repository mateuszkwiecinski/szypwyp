package pl.ccki.szypwyp.domain.queries

import io.reactivex.Observable
import pl.ccki.szypwyp.domain.base.InjectableMap
import pl.ccki.szypwyp.domain.base.Query
import pl.ccki.szypwyp.domain.base.SchedulersProvider
import pl.ccki.szypwyp.domain.base.applySchedulers
import pl.ccki.szypwyp.domain.models.FilterState
import pl.ccki.szypwyp.domain.models.LatLng
import pl.ccki.szypwyp.domain.models.PluginId
import pl.ccki.szypwyp.domain.models.compareTo
import pl.ccki.szypwyp.domain.models.distanceTo
import pl.ccki.szypwyp.domain.persistences.CurrentSearchTargetPersistence
import pl.ccki.szypwyp.domain.persistences.FiltersPersistence
import pl.ccki.szypwyp.domain.services.ExternalPlugin
import javax.inject.Inject

class GetFiltersQuery @Inject constructor(
    private val registeredPlugins: InjectableMap<PluginId, ExternalPlugin>,
    private val currentTarget: CurrentSearchTargetPersistence,
    private val filters: FiltersPersistence,
    private val schedulersProvider: SchedulersProvider
) : Query<Map<PluginId, FilterState>> {

    override fun execute(): Observable<Map<PluginId, FilterState>> =
        currentTarget.get()
            .map(this::findServicesCanSearchHere)
            .map(this::applyFilters)
            .applySchedulers(schedulersProvider)

    private fun findServicesCanSearchHere(target: LatLng) =
        registeredPlugins.filter { (_, plugin) ->
            plugin.supportedCities.any {
                it.center.distanceTo(target) < it.radius
            }
        }

    private fun applyFilters(services: Map<PluginId, ExternalPlugin>): Map<PluginId, FilterState> {
        val disabled = filters.disabled()
            .toSingle(emptySet())
            .blockingGet()

        return services.mapValues { (id, _) ->
            FilterState(!disabled.contains(id))
        }.takeIf { it.values.any { it.isEnabled } } ?: services.mapValues { FilterState(true) }
    }
}
