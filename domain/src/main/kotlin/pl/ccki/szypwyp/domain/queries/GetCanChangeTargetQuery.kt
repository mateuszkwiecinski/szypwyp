package pl.ccki.szypwyp.domain.queries

import io.reactivex.Observable
import pl.ccki.szypwyp.domain.base.Query
import pl.ccki.szypwyp.domain.persistences.PotentialSearchTargetPersistence
import javax.inject.Inject

class GetCanChangeTargetQuery @Inject constructor(
    private val persistence: PotentialSearchTargetPersistence
) : Query<Boolean> {

    override fun execute(): Observable<Boolean> =
        persistence.get()
            .map { !persistence.locked }
}
