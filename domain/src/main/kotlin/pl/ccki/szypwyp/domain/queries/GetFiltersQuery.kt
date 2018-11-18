package pl.ccki.szypwyp.domain.queries

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
) : Query<List<GetFiltersQuery.Item>> {

    data class Item(val pluginId: PluginId, val state: FilterState)

    override fun execute() =
        currentTarget.get()
            .map(this::findServicesCanSearchHere)
            .map(this::applyFilters)
            .map {
                // TODO: @mk 18/11/2018 is there any order?
                it.toList().map { Item(it.first, it.second) }
            }
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
