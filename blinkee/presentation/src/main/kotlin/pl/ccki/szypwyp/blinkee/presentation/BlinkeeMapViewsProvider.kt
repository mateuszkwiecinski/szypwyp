package pl.ccki.szypwyp.blinkee.presentation

import android.view.LayoutInflater
import android.view.View
import pl.ccki.szypwyp.blinkee.domain.models.BlinkeeMarkerModel
import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.presentation.interfaces.MapViewsProvider
import pl.ccki.szypwyp.presentation.interfaces.ServiceIcon
import javax.inject.Inject

class BlinkeeMapViewsProvider @Inject constructor() : MapViewsProvider<BlinkeeMarkerModel> {
    override val icon = ServiceIcon(R.drawable.ic_blinkee)

    override fun createInfoWindow(inflater: LayoutInflater, marker: BlinkeeMarkerModel): View? {
        return null
    }
}
