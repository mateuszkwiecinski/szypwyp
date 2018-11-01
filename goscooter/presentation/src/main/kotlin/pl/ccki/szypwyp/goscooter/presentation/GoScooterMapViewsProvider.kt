package pl.ccki.szypwyp.goscooter.presentation

import android.view.LayoutInflater
import android.view.View
import pl.ccki.szypwyp.goscooter.domain.models.GoScooterMarkerModel
import pl.ccki.szypwyp.presentation.interfaces.MapViewsProvider
import pl.ccki.szypwyp.presentation.interfaces.ServiceIcon
import javax.inject.Inject

class GoScooterMapViewsProvider @Inject constructor() : MapViewsProvider<GoScooterMarkerModel> {

    override val icon = ServiceIcon(R.drawable.is_goscooter)

    override fun createInfoWindow(inflater: LayoutInflater, marker: GoScooterMarkerModel): View? {
        return null
    }
}
