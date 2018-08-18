package pl.ccki.szypwyp.domain.persistences

import io.reactivex.Completable
import io.reactivex.Observable
import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.domain.models.ServiceId

interface VehiclesPersistence {
    fun get(): Observable<Map<ServiceId, List<MarkerModel>>>

    fun update(new: Map<ServiceId, List<MarkerModel>>): Completable
}
