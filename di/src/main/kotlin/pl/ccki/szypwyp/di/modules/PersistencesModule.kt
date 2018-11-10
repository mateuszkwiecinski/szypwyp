package pl.ccki.szypwyp.di.modules

import dagger.Binds
import dagger.Module
import pl.ccki.szypwyp.di.ApplicationSingleton
import pl.ccki.szypwyp.domain.persistences.CameraPersistence
import pl.ccki.szypwyp.domain.persistences.InMemoryCameraPersistence
import pl.ccki.szypwyp.domain.persistences.InMemorySearchTargetPersistence
import pl.ccki.szypwyp.domain.persistences.InMemoryVehiclesPersistence
import pl.ccki.szypwyp.domain.persistences.PotentialSearchTargetPersistence
import pl.ccki.szypwyp.domain.persistences.VehiclesPersistence

@Module
abstract class PersistencesModule {

    @Binds
    @ApplicationSingleton
    abstract fun vehiclesPersistence(implementation: InMemoryVehiclesPersistence): VehiclesPersistence

    @Binds
    @ApplicationSingleton
    abstract fun cameraPersistence(implementation: InMemoryCameraPersistence): CameraPersistence

    @Binds
    @ApplicationSingleton
    abstract fun searchTarget(implementation: InMemorySearchTargetPersistence): PotentialSearchTargetPersistence
}
