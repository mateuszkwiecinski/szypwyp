package pl.ccki.szypwyp.domain.persistences

import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.domain.models.ServiceInfoModel
import javax.inject.Inject
import javax.inject.Singleton

private typealias VehiclesData = Map<ServiceInfoModel, List<MarkerModel>>

interface VehiclesPersistence : DefaultPersistence<VehiclesData>

@Singleton
class InMemoryVehiclesPersistence @Inject constructor() : BehaviorSubjectBasedPersistence<VehiclesData>(emptyMap()), VehiclesPersistence
