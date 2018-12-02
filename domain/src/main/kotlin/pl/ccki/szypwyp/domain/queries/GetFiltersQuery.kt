package pl.ccki.szypwyp.domain.queries

import io.reactivex.Observable
import io.reactivex.functions.BiFunction
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

    data class Item(val pluginId: PluginId, val name: String, val state: FilterState)

    override fun execute(): Observable<List<GetFiltersQuery.Item>> {
        val targeting = currentTarget.get()
        val filtering = filters.observeDisabled().defaultIfEmpty(emptySet())

        return Observable.combineLatest<LatLng, Set<PluginId>, Map<PluginId, FilterState>>(targeting, filtering, BiFunction { target, filters ->
            val canSearchIn = findServicesCanSearchHere(target)
            applyFilters(canSearchIn, filters)
        })
            .map {
                it.toList().map { (id, state) ->
                    val name = registeredPlugins[id]?.name ?: id.id
                    Item(id, name, state)
                }.sortedBy(Item::name)
            }
            .applySchedulers(schedulersProvider)
    }

    private fun findServicesCanSearchHere(target: LatLng) =
        registeredPlugins.filter { (_, plugin) ->
            plugin.supportedCities.any {
                it.center.distanceTo(target) < it.radius
            }
        }

    private fun applyFilters(services: Map<PluginId, ExternalPlugin>, disabled: Set<PluginId>) =
        services.mapValues { (id, _) ->
            FilterState(!disabled.contains(id))
        }
}
