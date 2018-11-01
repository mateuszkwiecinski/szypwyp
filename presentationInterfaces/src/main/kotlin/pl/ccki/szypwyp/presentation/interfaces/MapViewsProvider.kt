package pl.ccki.szypwyp.presentation.interfaces

import android.view.LayoutInflater
import android.view.View
import pl.ccki.szypwyp.domain.models.MarkerModel

interface MapViewsProvider<T : MarkerModel> {

    val icon: ServiceIcon

    fun createInfoWindow(inflater: LayoutInflater, marker: T): View?
}
