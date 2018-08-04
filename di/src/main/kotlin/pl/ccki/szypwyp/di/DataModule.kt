package pl.ccki.szypwyp.di

import dagger.Module
import dagger.Provides
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import pl.ccki.szypwyp.domain.LatLng
import pl.ccki.szypwyp.domain.MarkerModel
import pl.ccki.szypwyp.domain.SearchConfigProvider
import pl.ccki.szypwyp.domain.ServiceConfigurationModel
import pl.ccki.szypwyp.domain.ServicesConfiguration
import pl.ccki.szypwyp.domain.VehiclesPersistence
import pl.ccki.szypwyp.domain.VehiclesRepository
import javax.inject.Singleton

@Module
class DataModule {
    @Provides
    fun servicesConfiguration(): ServicesConfiguration = object : ServicesConfiguration {

        override fun get(): Maybe<ServiceConfigurationModel> = Maybe.empty()
    }

    @Provides
    fun vehiclesRepository(factory: VehicleRepositoryFactory): VehiclesRepository = factory

    @Provides
    @Singleton
    fun vehiclesPersistence(): VehiclesPersistence = object : VehiclesPersistence {
        private var all = BehaviorSubject.createDefault<List<MarkerModel>>(emptyList())

        override fun get(): Observable<List<MarkerModel>> = all.hide()

        override fun update(new: List<MarkerModel>): Completable = Completable.fromAction {
            all.onNext(new)
        }
    }

    @Provides
    fun locationProvider(): SearchConfigProvider = object : SearchConfigProvider {

        override val target: LatLng? = LatLng(51.107883, 17.038538)
    }
}