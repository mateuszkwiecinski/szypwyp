package pl.ccki.szypwyp.presentation.map.vm

import androidx.lifecycle.MutableLiveData
import io.reactivex.subjects.BehaviorSubject
import pl.ccki.szypwyp.domain.base.execute
import pl.ccki.szypwyp.domain.commands.GetCameraCommand
import pl.ccki.szypwyp.domain.commands.GetVehiclesCommand
import pl.ccki.szypwyp.domain.models.Camera
import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.domain.queries.FindFirstCameraQuery
import pl.ccki.szypwyp.domain.queries.RefreshVehiclesQuery
import pl.ccki.szypwyp.presentation.base.BaseViewModel
import javax.inject.Inject

class MapViewModel @Inject constructor(
    getVehiclesCommand: GetVehiclesCommand,
    getCameraCommand: GetCameraCommand,
    private val refreshVehiclesQuery: RefreshVehiclesQuery,
    private val findFirstCameraQuery: FindFirstCameraQuery
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
        findFirstCameraQuery.execute()
            .andThen(refreshVehiclesQuery.execute())
            .subscribe()
    }
}
