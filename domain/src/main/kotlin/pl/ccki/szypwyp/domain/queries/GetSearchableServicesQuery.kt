package pl.ccki.szypwyp.domain.queries

import io.reactivex.Observable
import pl.ccki.szypwyp.domain.base.InjectableMap
import pl.ccki.szypwyp.domain.base.Query
import pl.ccki.szypwyp.domain.models.PluginId
import pl.ccki.szypwyp.domain.persistences.FiltersPersistence
import pl.ccki.szypwyp.domain.persistences.PotentialSearchTargetPersistence
import pl.ccki.szypwyp.domain.services.ExternalPlugin
import javax.inject.Inject

class GetSearchableServicesQuery @Inject constructor(
    private val registeredPlugins: InjectableMap<PluginId, ExternalPlugin>,
    private val filtersPersistence: FiltersPersistence
) : Query<Iterable<PluginId>> {
    override fun execute(): Observable<Iterable<PluginId>> {
        TODO("not implemented")
    }
}
