package pl.ccki.szypwyp.traficar.presentation

import android.view.LayoutInflater
import android.view.View
import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.presentation.interfaces.MapViewsProvider
import pl.ccki.szypwyp.presentation.interfaces.ServiceIcon
import pl.ccki.szypwyp.traficar.domain.models.TraficarMarkerModel
import javax.inject.Inject

class TraficarMapViewsProvider @Inject constructor() : MapViewsProvider<TraficarMarkerModel> {
    override val icon: ServiceIcon = ServiceIcon(R.drawable.ic_traficar)

    override fun createInfoWindow(inflater: LayoutInflater, marker: TraficarMarkerModel): View? {
        return null
    }
}
