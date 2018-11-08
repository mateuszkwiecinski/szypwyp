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
import pl.ccki.szypwyp.domain.commands.ChangeSearchTargetCommand
import pl.ccki.szypwyp.domain.commands.InitializeMapCommand
import pl.ccki.szypwyp.domain.commands.OpenExternalAppCommand
import pl.ccki.szypwyp.domain.commands.RefreshVehiclesCommand
import pl.ccki.szypwyp.domain.commands.UpdatePotentialSearchTargetCommand
import pl.ccki.szypwyp.domain.models.Camera
import pl.ccki.szypwyp.domain.models.LatLng
import pl.ccki.szypwyp.domain.models.Permission
import pl.ccki.szypwyp.domain.models.StateModel
import pl.ccki.szypwyp.domain.models.Zoom
import pl.ccki.szypwyp.domain.queries.GetCameraQuery
import pl.ccki.szypwyp.domain.queries.GetCanChangeTargetQuery
import pl.ccki.szypwyp.domain.queries.GetLocationQuery
import pl.ccki.szypwyp.domain.queries.GetMapProgressQuery
import pl.ccki.szypwyp.domain.queries.GetVehiclesQuery
import pl.ccki.szypwyp.domain.repositories.PermissionChecker
import pl.ccki.szypwyp.presentation.base.BaseViewModel
import pl.ccki.szypwyp.presentation.map.clustering.SingleClusterItem
import pl.ccki.szypwyp.presentation.map.models.LocationMode
import pl.ccki.szypwyp.presentation.map.models.MapEvent
import timber.log.Timber
import timber.log.info
import javax.inject.Inject

class MapViewModel @Inject constructor(
    getVehiclesQuery: GetVehiclesQuery,
    getCameraQuery: GetCameraQuery,
    getCanChangeTargetQuery: GetCanChangeTargetQuery,
    getMapProgressQuery: GetMapProgressQuery,
    private val getLocationQuery: GetLocationQuery,
    private val refreshVehiclesCommand: RefreshVehiclesCommand,
    private val initializeMapCommand: InitializeMapCommand,
    private val permissionChecker: PermissionChecker,
    private val openExternalAppCommand: OpenExternalAppCommand,
    private val updatePotentialSearchTargetCommand: UpdatePotentialSearchTargetCommand,
    private val changeSearchTargetCommand: ChangeSearchTargetCommand
) : BaseViewModel() {

    private var cameraUpdates: Disposable? = null

    val markers = getVehiclesQuery.execute().toLiveData(disposeBag)
    val camera = BehaviorSubject.create<Camera>()
    val canChangeTarget = getCanChangeTargetQuery.execute().toLiveData(disposeBag)
    val locationPermissionGranted = BehaviorSubject.create<Boolean>()
    val isLoadingSomething = getMapProgressQuery.execute()
        .map { it.values.any { it is StateModel.Loading } }
        .distinctUntilChanged()
        .toLiveData(disposeBag)
    val shouldShowError = getMapProgressQuery.execute()
        .map { it.values.any { it is StateModel.Failed } }
        .distinctUntilChanged()
        .toLiveData(disposeBag)
    val allProgress = getMapProgressQuery.execute().toLiveData(disposeBag)

    private val locationSubject = BehaviorSubject.createDefault(LocationMode.None)
    val locationMode = locationSubject.distinctUntilChanged().toLiveData(disposeBag)

    val navigation = PublishSubject.create<MapEvent>()
    var refreshDisposable: Disposable? = null

    init {
        getCameraQuery.execute {
            camera.onNext(it)
        }
        locationSubject
            .distinctUntilChanged()
            .subscribe { mode: LocationMode ->
                when (mode) {
                    LocationMode.ContinuousUpdates,
                    LocationMode.ZoomedUpdates -> {
                        cameraUpdates?.dispose()
                        cameraUpdates = getLocationQuery.execute()
                            .subscribe {
                                val zoom = if (mode == LocationMode.ZoomedUpdates) Zoom.Close else Zoom.Away
                                camera.onNext(Camera.ToPosition(it, maxZoom = zoom))
                            }
                            .also { disposeBag.add(it) }
                    }
                    LocationMode.None -> {
                        cameraUpdates?.dispose()
                    }
                }
            }
            .disposeIn(disposeBag)
    }

    fun onMapTouched() {
        locationSubject.onNext(LocationMode.None)
    }

    fun onFirstRun() {
        initializeMapCommand.execute()
            .subscribe()
            .disposeIn(disposeBag)
    }

    fun onMyLocationClicked() {
        checkPermission()
        if (locationPermissionGranted.value == true) {
            val newValue = when (locationSubject.value) {
                LocationMode.None, null -> LocationMode.ContinuousUpdates
                LocationMode.ContinuousUpdates -> LocationMode.ZoomedUpdates
                LocationMode.ZoomedUpdates -> LocationMode.ZoomedUpdates
            }
            locationSubject.onNext(newValue)
        } else {
            navigation.onNext(MapEvent.MyLocationPermissionError)
        }
    }

    fun onRefreshClicked() {
        if (refreshDisposable?.isDisposed != false) {
            refreshDisposable = refreshVehiclesCommand.execute()
                .subscribe()
        } else {
            Timber.info { "Loading in progress" }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun checkPermission() {
        locationPermissionGranted.onNext(permissionChecker.check(Permission.Location))
    }

    fun onItemClicked() {
        locationSubject.onNext(LocationMode.None)
    }

    fun onClusterClicked(items: Iterable<SingleClusterItem>) {
        locationSubject.onNext(LocationMode.None)
        camera.onNext(Camera.ToGroup(items.map { element -> element.position.let { LatLng(it.latitude, it.longitude) } }))
    }

    fun onInfoWindowClicked(item: SingleClusterItem) {
        openExternalAppCommand
            .execute(item.id)
            .subscribe()
            .disposeIn(disposeBag)
    }

    fun onCameraIdle(newTarget: LatLng) {
        updatePotentialSearchTargetCommand
            .execute(newTarget)
            .subscribe()
            .disposeIn(disposeBag)
    }

    fun onChangeSearchTarget() {
        changeSearchTargetCommand
            .execute()
            .subscribe()
            .disposeIn(disposeBag)
    }

    fun onInfoClicked() {

    }

    override fun onCleared() {
        super.onCleared()
        refreshDisposable?.dispose()
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
