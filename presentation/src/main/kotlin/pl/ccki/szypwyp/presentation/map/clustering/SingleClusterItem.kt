package pl.ccki.szypwyp.presentation.map.clustering

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem
import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.domain.models.ServiceInfoModel

class SingleClusterItem(
    val info: ServiceInfoModel,
    val marker: MarkerModel) : ClusterItem {

    override fun getSnippet(): String = marker.id

    override fun getTitle(): String = info.id

    override fun getPosition(): LatLng = marker.location.latLng
}

val pl.ccki.szypwyp.domain.models.LatLng.latLng: LatLng
    get() = LatLng(latitude, longitude)
