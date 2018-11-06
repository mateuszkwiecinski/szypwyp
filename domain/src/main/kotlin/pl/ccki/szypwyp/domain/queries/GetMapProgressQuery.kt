package pl.ccki.szypwyp.domain.queries

import io.reactivex.Observable
import pl.ccki.szypwyp.domain.base.Query
import pl.ccki.szypwyp.domain.models.Progress
import pl.ccki.szypwyp.domain.persistences.MapEventsPersistence
import javax.inject.Inject

class GetMapProgressQuery @Inject constructor(
    private val persistence: MapEventsPersistence
) : Query<Progress> {

    override fun execute(): Observable<Progress> =
        persistence.progress()
}
