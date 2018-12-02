package pl.ccki.szypwyp.domain.persistences

import pl.ccki.szypwyp.domain.models.LatLng
import pl.ccki.szypwyp.domain.repositories.SearchConfigRepository
import javax.inject.Inject

interface CurrentSearchTargetPersistence : DefaultPersistence<LatLng>

class InMemoryCurrentSearchTargetPersistence @Inject constructor(
    private val repository: SearchConfigRepository
) : BehaviorSubjectBasedPersistence<LatLng>({ repository.target }), CurrentSearchTargetPersistence
