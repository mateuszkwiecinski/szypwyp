package pl.ccki.szypwyp.nextbike.presentation

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.nextbike.domain.models.NextbikeMarkerModel
import pl.ccki.szypwyp.presentation.interfaces.MapViewsProvider
import pl.ccki.szypwyp.presentation.interfaces.generateBitmap
import pl.ccki.szypwyp.nextbike.presentation.databinding.ViewNextbikeInfoWindowBinding
import javax.inject.Inject

class NextbikeMapViewsProvider @Inject constructor() : MapViewsProvider {

    override fun createIcon(context: Context): Bitmap? =
        R.drawable.ic_nextbike.generateBitmap(context)

    override fun <T : MarkerModel> createInfoWindow(inflater: LayoutInflater, marker: T): View? {
        val binding = ViewNextbikeInfoWindowBinding.inflate(inflater, null, false)
        binding.model = marker as NextbikeMarkerModel
        binding.executePendingBindings()

        return binding.root
    }
}
