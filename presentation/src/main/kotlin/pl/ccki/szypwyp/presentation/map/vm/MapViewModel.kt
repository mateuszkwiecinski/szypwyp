package pl.ccki.szypwyp.presentation.map.vm

import androidx.lifecycle.MutableLiveData
import io.reactivex.subjects.BehaviorSubject
import pl.ccki.szypwyp.domain.base.execute
import pl.ccki.szypwyp.domain.commands.GetVehiclesCommand
import pl.ccki.szypwyp.domain.models.LatLng
import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.domain.queries.RefreshVehiclesQuery
import pl.ccki.szypwyp.presentation.base.BaseViewModel
import javax.inject.Inject

class MapViewModel @Inject constructor(
    getVehiclesCommand: GetVehiclesCommand,
    private val refreshVehiclesQuery: RefreshVehiclesQuery
) : BaseViewModel() {

    val markers = MutableLiveData<Map<String, List<MarkerModel>>>()

    init {
        getVehiclesCommand.execute {
            markers.value = it
        }
        refreshVehiclesQuery.execute().subscribe()
    }
}
