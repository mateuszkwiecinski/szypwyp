package pl.ccki.szypwyp.platform.implementations.location

import android.annotation.SuppressLint
import android.os.Looper
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.tasks.Tasks
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import pl.ccki.szypwyp.domain.errors.Android
import pl.ccki.szypwyp.domain.models.LatLng
import pl.ccki.szypwyp.domain.models.Permission
import pl.ccki.szypwyp.domain.providers.LocationProvider
import pl.ccki.szypwyp.domain.repositories.PermissionChecker
import pl.ccki.szypwyp.platform.PlatformSingleton
import javax.inject.Inject

@PlatformSingleton
class AndroidLocationProvider @Inject constructor(
    private val permissionChecker: PermissionChecker,
    private val fusedProvider: FusedLocationProviderClient
) : LocationProvider {

    @SuppressLint("MissingPermission")
    private val locationChanges =
        Observable.create<LatLng> { emitter ->
            Tasks.await(fusedProvider.lastLocation)?.let {
                emitter.onNext(LatLng(it.latitude, it.longitude))
            }

            val callback = object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult?) {
                    //this is called on MainThread due to passed Looper.getMainLooper() in `requestLocationUpdates`
                    locationResult?.lastLocation?.let {
                        emitter.onNext(LatLng(it.latitude, it.longitude))
                    }
                }
            }
            val request = LocationRequest().apply {
                priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
                fastestInterval = 1000
                interval = 5000
            }
            emitter.setCancellable { fusedProvider.removeLocationUpdates(callback) }
            Tasks.await(fusedProvider.requestLocationUpdates(request, callback, Looper.getMainLooper()))
        }
            .share()
            .distinct()

    override fun singleUpdate(scheduler: Scheduler): Single<LatLng> =
        if (permissionChecker.check(Permission.Location)) {
            locationChanges.take(1).singleOrError()
        } else {
            Single.error(Android.MissingPermission(Permission.Location))
        }
            .observeOn(scheduler)

    override fun observeChanges(scheduler: Scheduler): Observable<LatLng> =
        if (permissionChecker.check(Permission.Location)) {
            locationChanges.hide()
        } else {
            Observable.error(Android.MissingPermission(Permission.Location))
        }
            .observeOn(scheduler)
}
