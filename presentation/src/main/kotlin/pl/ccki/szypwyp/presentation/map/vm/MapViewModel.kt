package pl.ccki.szypwyp.presentation.map.vm

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import pl.ccki.szypwyp.domain.base.disposeIn
import pl.ccki.szypwyp.domain.base.execute
import pl.ccki.szypwyp.domain.commands.InitializeMapCommand
import pl.ccki.szypwyp.domain.commands.RefreshVehiclesCommand
import pl.ccki.szypwyp.domain.models.Camera
import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.domain.models.Permission
import pl.ccki.szypwyp.domain.models.Zoom
import pl.ccki.szypwyp.domain.queries.GetCameraQuery
import pl.ccki.szypwyp.domain.queries.GetLocationQuery
import pl.ccki.szypwyp.domain.queries.GetVehiclesQuery
import pl.ccki.szypwyp.domain.repositories.PermissionChecker
import pl.ccki.szypwyp.presentation.base.BaseViewModel
import pl.ccki.szypwyp.presentation.map.models.LocationMode
import pl.ccki.szypwyp.presentation.map.models.MapEvent
import javax.inject.Inject

class MapViewModel @Inject constructor(
    getVehiclesCommand: GetVehiclesQuery,
    getCameraCommand: GetCameraQuery,
    private val refreshVehiclesQuery: RefreshVehiclesCommand,
    private val initializeMapQuery: InitializeMapCommand,
    private val permissionChecker: PermissionChecker,
    getLocationQuery: GetLocationQuery
) : BaseViewModel() {

    private var cameraUpdates: Disposable? = null

    val markers = MutableLiveData<Map<String, List<MarkerModel>>>()
    val camera = BehaviorSubject.create<Camera>()
    val locationPermissionGranted = BehaviorSubject.create<Boolean>()

    private val locationSubject = BehaviorSubject.createDefault(LocationMode.None)
    val locationMode = locationSubject.distinctUntilChanged().toLiveData(disposeBag)

    val navigation = PublishSubject.create<MapEvent>()

    init {
        getVehiclesCommand.execute {
            markers.value = it
        }
        getCameraCommand.execute {
            camera.onNext(it)
        }
        locationSubject
            .distinctUntilChanged()
            .subscribe { mode: LocationMode ->
                when (mode) {
                    LocationMode.ContinuousUpdates -> {
                        cameraUpdates?.dispose()
                        cameraUpdates = getLocationQuery.execute()
                            .subscribe {
                                camera.onNext(Camera.ToPosition(it, maxZoom = Zoom.Away))
                            }.also { disposeBag.add(it) }
                    }
                    LocationMode.None -> {
                        cameraUpdates?.dispose()
                    }
                }
            }
            .disposeIn(disposeBag)
    }

    fun onMapTouched() {
        when (locationMode.value) {
            LocationMode.None -> null
            LocationMode.ContinuousUpdates, null -> LocationMode.None
        }?.let {
            locationSubject.onNext(it)
        }
    }

    fun onFirstRun() {
        initializeMapQuery.execute()
            .subscribe()
            .disposeIn(disposeBag)
    }

    fun onMyLocationClicked() {
        checkPermission()
        if (locationPermissionGranted.value == true) {
            locationSubject.onNext(LocationMode.ContinuousUpdates)
        } else {
            navigation.onNext(MapEvent.MyLocationPermissionError)
        }
    }

    fun onRefreshClicked() {
        refreshVehiclesQuery.execute()
            .subscribe()
            .disposeIn(disposeBag)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun checkPermission() {
        locationPermissionGranted.onNext(permissionChecker.check(Permission.Location))
    }
}

private fun <T> Observable<T>.toLiveData(disposeBag: CompositeDisposable): LiveData<T> {
    val mutableLiveData = MutableLiveData<T>()
    observeOn(AndroidSchedulers.mainThread())
        .subscribe {
            mutableLiveData.value = it
        }
        .disposeIn(disposeBag)
    return mutableLiveData
}
