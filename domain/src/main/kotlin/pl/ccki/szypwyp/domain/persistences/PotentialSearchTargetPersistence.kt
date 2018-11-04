package pl.ccki.szypwyp.domain.persistences

import io.reactivex.Maybe
import pl.ccki.szypwyp.domain.models.LatLng
import javax.inject.Inject
import javax.inject.Singleton

interface PotentialSearchTargetPersistence : DefaultPersistence<LatLng> {
    fun last(): Maybe<LatLng>
}

@Singleton
class InMemorySearchTargetPersistence @Inject constructor() : BehaviorSubjectBasedPersistence<LatLng>(), PotentialSearchTargetPersistence
