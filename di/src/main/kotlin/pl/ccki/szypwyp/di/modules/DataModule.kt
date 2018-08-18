package pl.ccki.szypwyp.di.modules

import dagger.Module
import dagger.Provides
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import pl.ccki.szypwyp.domain.models.LatLng
import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.domain.models.ServiceId
import pl.ccki.szypwyp.domain.persistences.VehiclesPersistence
import pl.ccki.szypwyp.domain.repositories.SearchConfigRepository
import pl.ccki.szypwyp.domain.repositories.ServicesConfigurationRepository
import javax.inject.Singleton

@Module
class DataModule {
    @Provides
    fun servicesConfiguration(): ServicesConfigurationRepository = object : ServicesConfigurationRepository {
        override var selected: Collection<ServiceId>? = null
    }

    @Provides
    @Singleton
    fun vehiclesPersistence(): VehiclesPersistence = object : VehiclesPersistence {
        private val all = BehaviorSubject.createDefault(emptyMap<ServiceId, List<MarkerModel>>())

        override fun get(): Observable<Map<ServiceId, List<MarkerModel>>> = all.hide()

        override fun update(new: Map<ServiceId, List<MarkerModel>>): Completable = Completable.fromAction {
            all.onNext(new)
        }
    }

    @Provides
    fun locationProvider(): SearchConfigRepository = object : SearchConfigRepository {

        override val target: LatLng? = LatLng(51.107883, 17.038538)
    }
}
