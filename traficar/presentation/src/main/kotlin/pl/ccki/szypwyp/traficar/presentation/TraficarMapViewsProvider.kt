package pl.ccki.szypwyp.traficar.presentation

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.presentation.interfaces.MapViewsProvider
import pl.ccki.szypwyp.presentation.interfaces.generateBitmap
import pl.ccki.szypwyp.traficar.domain.models.TraficarMarkerModel
import pl.ccki.szypwyp.traficar.presentation.databinding.ViewTraficarInfoWindowBinding
import javax.inject.Inject

class TraficarMapViewsProvider @Inject constructor() : MapViewsProvider {

    override fun createIcon(context: Context, marker: MarkerModel): Bitmap? =
        R.drawable.ic_traficar.generateBitmap(context)

    override fun createInfoWindow(inflater: LayoutInflater, marker: MarkerModel): View {
        val binding = ViewTraficarInfoWindowBinding.inflate(inflater, null, false)
        binding.model = marker as TraficarMarkerModel
        binding.executePendingBindings()

        return binding.root
    }
}
