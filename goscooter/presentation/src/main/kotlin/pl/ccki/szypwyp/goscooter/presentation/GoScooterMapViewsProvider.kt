package pl.ccki.szypwyp.goscooter.presentation

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.goscooter.domain.models.GoScooterMarkerModel
import pl.ccki.szypwyp.presentation.interfaces.MapViewsProvider
import pl.ccki.szypwyp.presentation.interfaces.generateBitmap
import javax.inject.Inject

class GoScooterMapViewsProvider @Inject constructor() : MapViewsProvider {
    override fun createIcon(context : Context): Bitmap? =
        R.drawable.ic_goscooter.generateBitmap(context)

    override fun <T : MarkerModel> createInfoWindow(inflater: LayoutInflater, marker: T) : View? = null
}
