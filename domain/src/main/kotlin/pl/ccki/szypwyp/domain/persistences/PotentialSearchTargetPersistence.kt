package pl.ccki.szypwyp.domain.persistences

import io.reactivex.Maybe
import pl.ccki.szypwyp.domain.models.LatLng
import pl.ccki.szypwyp.domain.repositories.SearchConfigRepository
import javax.inject.Inject

interface PotentialSearchTargetPersistence : DefaultPersistence<LatLng> {
    fun last(): Maybe<LatLng>
    var locked: Boolean
}

class InMemorySearchTargetPersistence @Inject constructor(
    private val repository: SearchConfigRepository
) : BehaviorSubjectBasedPersistence<LatLng>({ repository.target }), PotentialSearchTargetPersistence {
    override var locked: Boolean = true
}
