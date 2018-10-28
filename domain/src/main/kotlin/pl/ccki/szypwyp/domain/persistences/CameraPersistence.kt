package pl.ccki.szypwyp.domain.persistences

import pl.ccki.szypwyp.domain.models.Camera
import javax.inject.Inject
import javax.inject.Singleton

interface CameraPersistence : DefaultPersistence<Camera>

@Singleton
class InMemoryCameraPersistence @Inject constructor() : BehaviorSubjectBasedPersistence<Camera>(), CameraPersistence
