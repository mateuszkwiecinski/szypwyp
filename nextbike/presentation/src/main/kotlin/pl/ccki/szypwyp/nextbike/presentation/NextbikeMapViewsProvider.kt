package pl.ccki.szypwyp.nextbike.presentation

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.nextbike.domain.models.NextbikeMarkerModel
import pl.ccki.szypwyp.nextbike.presentation.databinding.ViewNextbikeInfoWindowBinding
import pl.ccki.szypwyp.presentation.interfaces.MapViewsProvider
import pl.ccki.szypwyp.presentation.interfaces.generateBitmap
import javax.inject.Inject

class NextbikeMapViewsProvider @Inject constructor() : MapViewsProvider {

    override fun createIcon(context: Context, marker: MarkerModel): Bitmap? {
        val data = (marker as NextbikeMarkerModel)

        return when (data.bikes) {
            0 -> R.drawable.ic_nextbike_empty
            else -> R.drawable.ic_nextbike
        }.generateBitmap(context)
    }

    override fun createInfoWindow(inflater: LayoutInflater, marker: MarkerModel): View? {
        val binding = ViewNextbikeInfoWindowBinding.inflate(inflater, null, false)
        binding.model = marker as NextbikeMarkerModel
        binding.executePendingBindings()

        return binding.root
    }
}
