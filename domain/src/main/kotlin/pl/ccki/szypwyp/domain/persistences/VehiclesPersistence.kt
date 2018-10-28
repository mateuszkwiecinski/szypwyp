package pl.ccki.szypwyp.domain.persistences

import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.domain.models.ServiceId
import javax.inject.Inject
import javax.inject.Singleton

private typealias VehiclesData = Map<ServiceId, List<MarkerModel>>

interface VehiclesPersistence : DefaultPersistence<VehiclesData>

@Singleton
class InMemoryVehiclesPersistence @Inject constructor() : BehaviorSubjectBasedPersistence<VehiclesData>(emptyMap()), VehiclesPersistence
