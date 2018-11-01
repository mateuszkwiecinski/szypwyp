package pl.ccki.szypwyp.vozilla.presentation

import android.view.LayoutInflater
import android.view.View
import pl.ccki.szypwyp.presentation.interfaces.MapViewsProvider
import pl.ccki.szypwyp.presentation.interfaces.ServiceIcon
import pl.ccki.szypwyp.vozilla.domain.VozillaMarkerModel
import pl.ccki.szypwyp.vozilla.presentation.databinding.ViewInfoWindowBinding
import javax.inject.Inject

class VozillaMapViewsProvider @Inject constructor() : MapViewsProvider<VozillaMarkerModel> {

    override val icon: ServiceIcon
        get() = ServiceIcon(R.drawable.ic_vozilla)

    override fun createInfoWindow(inflater: LayoutInflater, marker: VozillaMarkerModel): View {
        val binding = ViewInfoWindowBinding.inflate(inflater, null, false)
        binding.model = marker
        binding.executePendingBindings()

        return binding.root
    }
}
