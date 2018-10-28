package pl.ccki.szypwyp.domain.queries

import io.reactivex.Observable
import pl.ccki.szypwyp.domain.base.Query
import pl.ccki.szypwyp.domain.base.SchedulersProvider
import pl.ccki.szypwyp.domain.base.applySchedulers
import pl.ccki.szypwyp.domain.models.LatLng
import pl.ccki.szypwyp.domain.providers.LocationProvider
import javax.inject.Inject

class GetLocationQuery @Inject constructor(
    private val locationProvider: LocationProvider,
    private val schedulersProvider: SchedulersProvider
) : Query<LatLng> {

    override fun execute(): Observable<LatLng> =
        locationProvider.observeChanges(schedulersProvider.worker)
            .applySchedulers(schedulersProvider)
}
