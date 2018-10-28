package pl.ccki.szypwyp.presentation.map.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnCameraMoveStartedListener.REASON_GESTURE
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLngBounds
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import pl.ccki.szypwyp.domain.base.disposeIn
import pl.ccki.szypwyp.domain.models.Camera
import pl.ccki.szypwyp.domain.models.LatLng
import pl.ccki.szypwyp.domain.models.Zoom
import pl.ccki.szypwyp.presentation.base.extensions.observe
import pl.ccki.szypwyp.presentation.map.clustering.MapCluterManager
import pl.ccki.szypwyp.presentation.map.vm.MapViewModel
import com.google.android.gms.maps.model.LatLng as GoogleLatLng

@SuppressLint("MissingPermission")
class SzypMap(
    context: Context,
    googleMap: GoogleMap,
    viewModel: MapViewModel,
    lifecycleOwner: LifecycleOwner,
    savedInstanceState: Bundle?,
    private val disposeBag: CompositeDisposable = CompositeDisposable()
) : Disposable by disposeBag {

    private val clusterManager = MapCluterManager(context, googleMap)

    init {
        googleMap.clear()
        viewModel.markers.observe(lifecycleOwner) {
            clusterManager.items = it.orEmpty()
        }
        viewModel.camera.take(1).subscribe {
            if (savedInstanceState == null) {
                googleMap.moveCamera(it.toCameraUpdate(googleMap.cameraPosition))
            }
        }.disposeIn(disposeBag)
        viewModel.camera.skip(1).subscribe {
            googleMap.animateCamera(it.toCameraUpdate(googleMap.cameraPosition))
        }.disposeIn(disposeBag)

        googleMap.uiSettings.isMyLocationButtonEnabled = false
        viewModel.locationPermissionGranted.subscribe {
            googleMap.isMyLocationEnabled = it
        }.disposeIn(disposeBag)
        googleMap.setOnCameraMoveStartedListener {
            when (it) {
                REASON_GESTURE -> {
                    viewModel.onMapTouched()
                }
            }
        }
    }
}

private fun Camera.toCameraUpdate(camera: CameraPosition): CameraUpdate =
    when (this) {
        is Camera.ToPosition -> {
            val current = camera.zoom
            val max = maxZoom?.toFloat() ?: current
            val min = minZoom?.toFloat() ?: current

            when{
                current < max -> CameraUpdateFactory.newLatLngZoom(position.toLatLng(), max)
                current > min -> CameraUpdateFactory.newLatLngZoom(position.toLatLng(), min)
                else -> CameraUpdateFactory.newLatLng(position.toLatLng())
            }
        }
        is Camera.ToGroup -> {
            val builder = LatLngBounds.builder()
            items.map(LatLng::toLatLng).forEach { builder.include(it) }

            CameraUpdateFactory.newLatLngBounds(builder.build(), 100)
        }
        Camera.ZoomIn -> CameraUpdateFactory.zoomIn()
        Camera.ZoomOut -> CameraUpdateFactory.zoomOut()
    }

private fun Zoom.toFloat(): Float =
    when (this) {
        Zoom.Close -> 16f
        Zoom.Away -> 12f
    }

private fun LatLng.toLatLng(): GoogleLatLng =
    GoogleLatLng(latitude, longitude)
