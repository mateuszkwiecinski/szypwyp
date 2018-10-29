package pl.ccki.szypwyp.presentation.map.clustering

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer
import pl.ccki.szypwyp.domain.models.ServiceInfoModel

class ClusterIconRenderer(
    private val context: Context,
    googleMap: GoogleMap,
    manager: ClusterManager<SingleClusterItem>
) : DefaultClusterRenderer<SingleClusterItem>(context, googleMap, manager) {

    private val cache = mutableMapOf<String, BitmapDescriptor>()
    override fun onBeforeClusterItemRendered(item: SingleClusterItem, markerOptions: MarkerOptions) {
        val icon = cache.getOrPut(item.info.id) { createIcon(item.info) }
        markerOptions.icon(icon)
    }

    private fun createIcon(item: ServiceInfoModel): BitmapDescriptor =
        item.icon?.let {
            ContextCompat.getDrawable(context, it)
        }?.let {
            getMarkerIconFromDrawable(it)
        } ?: BitmapDescriptorFactory.defaultMarker()

    private fun getMarkerIconFromDrawable(drawable: Drawable): BitmapDescriptor {
        val canvas = Canvas()
        val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        canvas.setBitmap(bitmap)
        drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
        drawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }
}
