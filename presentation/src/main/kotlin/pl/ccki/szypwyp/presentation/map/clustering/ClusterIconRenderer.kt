package pl.ccki.szypwyp.presentation.map.clustering

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer
import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.domain.models.PluginId
import pl.ccki.szypwyp.presentation.interfaces.MapViewsProvider

class ClusterIconRenderer(
    private val context: Context,
    private val viewsProvider: Map<PluginId, MapViewsProvider>,
    googleMap: GoogleMap,
    manager: ClusterManager<SingleClusterItem>
) : DefaultClusterRenderer<SingleClusterItem>(context, googleMap, manager) {

    private val cache = mutableMapOf<PluginId, BitmapDescriptor>()
    override fun onBeforeClusterItemRendered(item: SingleClusterItem, markerOptions: MarkerOptions) {
        val icon = cache.getOrPut(item.id) { createIcon(item.id) }
        markerOptions.icon(icon)
    }

    override fun onClusterItemRendered(clusterItem: SingleClusterItem, marker: Marker) {
        marker.tag = clusterItem
    }

    private fun createIcon(item: PluginId): BitmapDescriptor =
        viewsProvider[item]?.createIcon(context)
            ?.let(BitmapDescriptorFactory::fromBitmap)
            ?: BitmapDescriptorFactory.defaultMarker()
}
