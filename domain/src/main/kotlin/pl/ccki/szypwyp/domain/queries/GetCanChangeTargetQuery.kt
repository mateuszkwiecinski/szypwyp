package pl.ccki.szypwyp.domain.queries

import io.reactivex.Observable
import pl.ccki.szypwyp.domain.base.Query
import pl.ccki.szypwyp.domain.persistences.PotentialSearchTargetPersistence
import pl.ccki.szypwyp.domain.repositories.SearchConfigRepository
import javax.inject.Inject

class GetCanChangeTargetQuery @Inject constructor(
    private val persistence: PotentialSearchTargetPersistence,
    private val repository: SearchConfigRepository
) : Query<Boolean> {

    override fun execute(): Observable<Boolean> =
        persistence.get()
            .map { repository.target != it }
}
