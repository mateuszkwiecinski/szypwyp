package pl.ccki.szypwyp.presentation.map.clustering

import android.content.Context
import com.google.android.gms.maps.GoogleMap
import com.google.maps.android.clustering.ClusterManager
import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.domain.models.PluginId
import pl.ccki.szypwyp.presentation.interfaces.MapViewsProvider
import pl.ccki.szypwyp.presentation.map.ui.toLatLng
import pl.ccki.szypwyp.presentation.map.vm.MapViewModel

class MapCluterManager(
    context: Context,
    map: GoogleMap,
    viewsProvider : Map<PluginId, MapViewsProvider<MarkerModel>>,
    private val viewModel: MapViewModel
) {

    var items: Map<PluginId, List<MarkerModel>> = emptyMap()
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
            map.setOnCameraIdleListener{
                with(map.cameraPosition){
                    viewModel.onCameraIdle(target.toLatLng())
                }
                it.onCameraIdle()
            }
            map.setOnInfoWindowClickListener(it)
            it.setOnClusterItemClickListener { item ->
                viewModel.onItemClicked(item)
                false
            }
            it.setOnClusterClickListener { cluster ->
                viewModel.onClusterClicked(cluster.items)
                true
            }
            it.renderer = ClusterIconRenderer(context, viewsProvider, map, it)
            it.setOnClusterItemInfoWindowClickListener {
                viewModel.onInfoWindowClicked(it)
            }
        }
    }
}
