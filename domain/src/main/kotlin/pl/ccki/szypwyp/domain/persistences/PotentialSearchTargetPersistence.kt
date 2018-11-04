package pl.ccki.szypwyp.domain.persistences

import io.reactivex.Maybe
import pl.ccki.szypwyp.domain.models.LatLng
import pl.ccki.szypwyp.domain.repositories.SearchConfigRepository
import javax.inject.Inject
import javax.inject.Singleton

interface PotentialSearchTargetPersistence : DefaultPersistence<LatLng> {
    fun last(): Maybe<LatLng>
}

@Singleton
class InMemorySearchTargetPersistence @Inject constructor(
    private val repository: SearchConfigRepository
) : BehaviorSubjectBasedPersistence<LatLng>({ repository.target }), PotentialSearchTargetPersistence
