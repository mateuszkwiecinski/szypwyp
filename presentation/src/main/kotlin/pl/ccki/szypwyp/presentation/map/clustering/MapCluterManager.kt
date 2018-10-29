package pl.ccki.szypwyp.presentation.map.clustering

import android.content.Context
import com.google.android.gms.maps.GoogleMap
import com.google.maps.android.clustering.ClusterManager
import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.domain.models.ServiceInfoModel
import pl.ccki.szypwyp.presentation.map.vm.MapViewModel

class MapCluterManager(
    context: Context,
    map: GoogleMap,
    private val viewModel: MapViewModel
) {

    var items: Map<ServiceInfoModel, List<MarkerModel>> = emptyMap()
        set(value) {
            field = value
            val all = value.toList().map { (info, markers) ->
                markers.map { SingleClusterItem(info, it) }
            }.flatten()
            clusterManager.clearItems()
            clusterManager.addItems(all)
            clusterManager.cluster()
        }

    private val clusterManager by lazy {
        ClusterManager<SingleClusterItem>(context, map).also {
            map.setOnMarkerClickListener(it)
            map.setOnCameraIdleListener(it)
            map.setOnInfoWindowClickListener(it)
            it.setOnClusterItemClickListener { item ->
                viewModel.onItemClicked(item)
                false
            }
            it.setOnClusterClickListener { cluster ->
                viewModel.onClusterClicked(cluster.items)
                true
            }
            it.renderer = ClusterIconRenderer(context, map, it)
        }
    }
}
