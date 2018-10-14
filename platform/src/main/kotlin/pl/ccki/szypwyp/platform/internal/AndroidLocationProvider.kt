package pl.ccki.szypwyp.platform.internal

import android.annotation.SuppressLint
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.tasks.Tasks
import io.reactivex.Single
import pl.ccki.szypwyp.domain.models.LatLng
import pl.ccki.szypwyp.domain.models.Permission
import pl.ccki.szypwyp.domain.providers.LocationProvider
import java.util.concurrent.ExecutionException
import javax.inject.Inject

class AndroidLocationProvider @Inject constructor(
    private val permissionChecker: AndroidPermissionChecker,
    private val fusedProvider: FusedLocationProviderClient
) : LocationProvider {

    override fun singleUpdate(): Single<LatLng> =
        if (permissionChecker.checkPermission(Permission.Location)) {
            requestSingleUpdate()
        } else {
            Single.error(Throwable())
        }

    @SuppressLint("MissingPermission")
    private fun requestSingleUpdate(): Single<LatLng> =
        Single.create { emitter ->
            try {
                Tasks.await(fusedProvider.lastLocation)?.let {
                    emitter.onSuccess(LatLng(it.latitude, it.longitude))
                }
            } catch (error: ExecutionException) {
                // ignored
            }
        }
}
