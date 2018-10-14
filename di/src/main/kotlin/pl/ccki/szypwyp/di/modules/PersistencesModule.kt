package pl.ccki.szypwyp.di.modules

import dagger.Module
import dagger.Provides
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import pl.ccki.szypwyp.domain.models.Camera
import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.domain.models.ServiceId
import pl.ccki.szypwyp.domain.persistences.CameraPersistence
import pl.ccki.szypwyp.domain.persistences.VehiclesPersistence
import javax.inject.Singleton

@Module
class PersistencesModule {

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
    @Singleton
    fun cameraPersistence(): CameraPersistence = object : CameraPersistence {
        private val subject = BehaviorSubject.create<Camera>()

        override fun get(): Observable<Camera> = subject.hide()

        override fun update(new: Camera): Completable =
            Completable.fromAction {
                subject.onNext(new)
            }
    }
}
