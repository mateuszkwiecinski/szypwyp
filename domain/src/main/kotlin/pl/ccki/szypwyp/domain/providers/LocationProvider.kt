package pl.ccki.szypwyp.domain.providers

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import pl.ccki.szypwyp.domain.models.LatLng

interface LocationProvider {
    fun singleUpdate(scheduler: Scheduler): Single<LatLng>
    fun observeChanges(scheduler: Scheduler): Observable<LatLng>
}
