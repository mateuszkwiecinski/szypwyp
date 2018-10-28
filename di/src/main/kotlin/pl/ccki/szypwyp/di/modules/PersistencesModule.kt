package pl.ccki.szypwyp.di.modules

import dagger.Binds
import dagger.Module
import pl.ccki.szypwyp.domain.persistences.CameraPersistence
import pl.ccki.szypwyp.domain.persistences.InMemoryCameraPersistence
import pl.ccki.szypwyp.domain.persistences.InMemoryVehiclesPersistence
import pl.ccki.szypwyp.domain.persistences.VehiclesPersistence

@Module
abstract class PersistencesModule {

    @Binds
    abstract fun vehiclesPersistence(implementation: InMemoryVehiclesPersistence): VehiclesPersistence

    @Binds
    abstract fun cameraPersistence(implementation: InMemoryCameraPersistence): CameraPersistence
}
