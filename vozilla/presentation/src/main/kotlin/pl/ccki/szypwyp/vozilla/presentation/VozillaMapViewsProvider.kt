package pl.ccki.szypwyp.vozilla.presentation

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import pl.ccki.szypwyp.presentation.interfaces.MapViewsProvider
import pl.ccki.szypwyp.presentation.interfaces.generateBitmap
import pl.ccki.szypwyp.vozilla.domain.VozillaMarkerModel
import pl.ccki.szypwyp.vozilla.presentation.databinding.ViewInfoWindowBinding
import javax.inject.Inject

class VozillaMapViewsProvider @Inject constructor() : MapViewsProvider<VozillaMarkerModel> {

    override fun createIcon(context: Context): Bitmap? =
        R.drawable.ic_vozilla.generateBitmap(context)

    override fun createInfoWindow(inflater: LayoutInflater, marker: VozillaMarkerModel): View {
        val binding = ViewInfoWindowBinding.inflate(inflater, null, false)
        binding.model = marker
        binding.executePendingBindings()

        return binding.root
    }
}
