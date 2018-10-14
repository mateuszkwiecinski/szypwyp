package pl.ccki.szypwyp.presentation.map.vm

import androidx.lifecycle.MutableLiveData
import io.reactivex.subjects.BehaviorSubject
import pl.ccki.szypwyp.domain.base.execute
import pl.ccki.szypwyp.domain.queries.GetCameraQuery
import pl.ccki.szypwyp.domain.queries.GetVehiclesQuery
import pl.ccki.szypwyp.domain.models.Camera
import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.domain.commands.InitializeMapCommand
import pl.ccki.szypwyp.domain.commands.RefreshVehiclesCommand
import pl.ccki.szypwyp.presentation.base.BaseViewModel
import javax.inject.Inject

class MapViewModel @Inject constructor(
    getVehiclesCommand: GetVehiclesQuery,
    getCameraCommand: GetCameraQuery,
    private val refreshVehiclesQuery: RefreshVehiclesCommand,
    private val initializeMapQuery: InitializeMapCommand
) : BaseViewModel() {

    val markers = MutableLiveData<Map<String, List<MarkerModel>>>()
    val camera = BehaviorSubject.create<Camera>()

    init {
        getVehiclesCommand.execute {
            markers.value = it
        }
        getCameraCommand.execute {
            camera.onNext(it)
        }
    }

    fun onFirstRun() {
        initializeMapQuery.execute()
            .subscribe()
    }
}
