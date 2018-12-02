package pl.ccki.szypwyp.domain.persistences

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.domain.models.PluginId
import javax.inject.Inject

typealias VehiclesData = Map<PluginId, List<MarkerModel>>

interface VehiclesPersistence {
    fun get(): Observable<VehiclesData>
    fun update(id: PluginId, data: List<MarkerModel>): Completable
    fun clear(): Completable
}

class InMemoryVehiclesPersistence @Inject constructor() : VehiclesPersistence {
    private val subject = BehaviorSubject.createDefault<VehiclesData>(emptyMap())

    override fun get(): Observable<VehiclesData> = subject.hide()

    override fun update(id: PluginId, data: List<MarkerModel>): Completable =
        Completable.fromAction {
            val newValue = subject.value.orEmpty().toMutableMap()
            newValue[id] = data
            subject.onNext(newValue)
        }

    override fun clear() = Completable.fromAction {
        subject.onNext(emptyMap())
    }
}
