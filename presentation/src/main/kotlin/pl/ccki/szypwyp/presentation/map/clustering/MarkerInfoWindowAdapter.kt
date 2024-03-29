package pl.ccki.szypwyp.presentation.map.clustering

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import pl.ccki.szypwyp.domain.models.PluginId
import pl.ccki.szypwyp.presentation.databinding.ViewMapDefaultInfoWindowBinding
import pl.ccki.szypwyp.presentation.interfaces.MapViewsProvider

class MarkerInfoWindowAdapter(
    context: Context,
    private val viewsProvider: Map<PluginId, MapViewsProvider>
) : GoogleMap.InfoWindowAdapter {

    private val inflater by lazy {
        LayoutInflater.from(context)
    }

    override fun getInfoWindow(marker: Marker): View? = null

    override fun getInfoContents(marker: Marker): View? {
        val item = marker.tag as? SingleClusterItem ?: return null

        return viewsProvider[item.id]?.createInfoWindow(inflater, item.marker) ?: defaultWindow(item)
    }

    private fun defaultWindow(item: SingleClusterItem): View {
        val binding = ViewMapDefaultInfoWindowBinding.inflate(inflater, null, false)
        binding.model = item.marker
        binding.executePendingBindings()

        return binding.root
    }
}
