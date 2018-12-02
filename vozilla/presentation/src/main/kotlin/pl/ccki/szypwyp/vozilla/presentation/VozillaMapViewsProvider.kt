package pl.ccki.szypwyp.vozilla.presentation

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.presentation.interfaces.MapViewsProvider
import pl.ccki.szypwyp.presentation.interfaces.generateBitmap
import pl.ccki.szypwyp.vozilla.domain.models.VozillaMarkerModel
import pl.ccki.szypwyp.vozilla.presentation.databinding.ViewVozillaInfoWindowBinding
import javax.inject.Inject

class VozillaMapViewsProvider @Inject constructor() : MapViewsProvider {

    override fun createIcon(context: Context): Bitmap? =
        R.drawable.ic_vozilla.generateBitmap(context)

    override fun <T : MarkerModel> createInfoWindow(inflater: LayoutInflater, marker: T): View {
        val binding = ViewVozillaInfoWindowBinding.inflate(inflater, null, false)
        binding.model = marker as VozillaMarkerModel
        binding.executePendingBindings()

        return binding.root
    }
}
