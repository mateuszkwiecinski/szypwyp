package pl.ccki.szypwyp.domain.providers

import io.reactivex.Single
import pl.ccki.szypwyp.domain.models.LatLng

interface LocationProvider {
    fun singleUpdate(): Single<LatLng>
}
