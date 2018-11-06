package pl.ccki.szypwyp.domain.queries

import io.reactivex.Observable
import pl.ccki.szypwyp.domain.base.Query
import pl.ccki.szypwyp.domain.models.MapError
import pl.ccki.szypwyp.domain.persistences.MapEventsPersistence
import javax.inject.Inject

class GetMapErrorsQuery @Inject constructor(
    private val persistence: MapEventsPersistence
) : Query<MapError> {

    override fun execute(): Observable<MapError> =
        persistence.errors()
}
