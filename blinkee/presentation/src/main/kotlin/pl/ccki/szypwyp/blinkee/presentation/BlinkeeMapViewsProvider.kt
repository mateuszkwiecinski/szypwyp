package pl.ccki.szypwyp.blinkee.presentation

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import pl.ccki.szypwyp.blinkee.domain.models.BlinkeeMarkerModel
import pl.ccki.szypwyp.presentation.interfaces.MapViewsProvider
import pl.ccki.szypwyp.presentation.interfaces.generateBitmap
import javax.inject.Inject

class BlinkeeMapViewsProvider @Inject constructor() : MapViewsProvider<BlinkeeMarkerModel> {

    override fun createIcon(context: Context): Bitmap? =
        R.drawable.ic_blinkee.generateBitmap(context)

    override fun createInfoWindow(inflater: LayoutInflater, marker: BlinkeeMarkerModel): View? {
        return null
    }
}
