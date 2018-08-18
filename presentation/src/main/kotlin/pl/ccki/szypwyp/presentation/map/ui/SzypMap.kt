package pl.ccki.szypwyp.presentation.map.ui

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.google.android.gms.maps.GoogleMap
import pl.ccki.szypwyp.presentation.base.extensions.observe
import pl.ccki.szypwyp.presentation.map.clustering.MapCluterManager
import pl.ccki.szypwyp.presentation.map.vm.MapViewModel

class SzypMap(
    context: Context,
    googleMap: GoogleMap,
    viewModel: MapViewModel,
    lifecycleOwner: LifecycleOwner
) {

    val clusterManager = MapCluterManager(context, googleMap)

    init {
        googleMap.clear()
        viewModel.markers.observe(lifecycleOwner) {
            clusterManager.items = it.orEmpty()
        }
    }
}
