package pl.ccki.szypwyp.presentation.base.extensions

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import io.reactivex.Single

fun SupportMapFragment.rxGetMap(): Single<GoogleMap> =
    Single.create { emitter ->
        getMapAsync { emitter.onSuccess(it) }
    }
