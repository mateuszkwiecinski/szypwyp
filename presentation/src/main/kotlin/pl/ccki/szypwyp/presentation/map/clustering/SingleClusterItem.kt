package pl.ccki.szypwyp.presentation.map.clustering

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem
import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.domain.models.PluginId

class SingleClusterItem(
    val id: PluginId,
    val marker: MarkerModel) : ClusterItem {

    override fun getSnippet(): String = marker.id

    override fun getTitle(): String = id.id

    override fun getPosition(): LatLng = marker.location.latLng
}

val pl.ccki.szypwyp.domain.models.LatLng.latLng: LatLng
    get() = LatLng(latitude, longitude)
