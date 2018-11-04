package pl.ccki.szypwyp.presentation.map.clustering

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem
import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.domain.models.PluginId
import pl.ccki.szypwyp.presentation.map.ui.latLng

class SingleClusterItem(
    val id: PluginId,
    val marker: MarkerModel) : ClusterItem {

    override fun getSnippet(): String = marker.name

    override fun getTitle(): String = id.id

    override fun getPosition(): LatLng = marker.location.latLng
}
