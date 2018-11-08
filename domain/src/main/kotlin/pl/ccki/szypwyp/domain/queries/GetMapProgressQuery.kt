package pl.ccki.szypwyp.domain.queries

import io.reactivex.Observable
import pl.ccki.szypwyp.domain.base.Query
import pl.ccki.szypwyp.domain.base.SchedulersProvider
import pl.ccki.szypwyp.domain.base.applySchedulers
import pl.ccki.szypwyp.domain.models.PluginId
import pl.ccki.szypwyp.domain.models.StateModel
import pl.ccki.szypwyp.domain.persistences.MapEventsPersistence
import javax.inject.Inject

class GetMapProgressQuery @Inject constructor(
    private val persistence: MapEventsPersistence,
    private val schedulersProvider: SchedulersProvider
) : Query<Map<PluginId, StateModel>> {

    override fun execute(): Observable<Map<PluginId, StateModel>> =
        persistence.events()
            .applySchedulers(schedulersProvider)
}
