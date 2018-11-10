package pl.ccki.szypwyp.domain.persistences

import pl.ccki.szypwyp.domain.models.Camera
import javax.inject.Inject

interface CameraPersistence : DefaultPersistence<Camera>

class InMemoryCameraPersistence @Inject constructor() : BehaviorSubjectBasedPersistence<Camera>(), CameraPersistence
