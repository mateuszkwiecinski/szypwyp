package pl.ccki.szypwyp.presentation.map.ui

import android.os.Bundle
import com.google.android.gms.maps.SupportMapFragment
import com.tbruyelle.rxpermissions2.RxPermissions
import pl.ccki.szypwyp.domain.base.InjectableMap
import pl.ccki.szypwyp.domain.base.disposeIn
import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.domain.models.Permission
import pl.ccki.szypwyp.domain.models.PluginId
import pl.ccki.szypwyp.presentation.MainActivity
import pl.ccki.szypwyp.presentation.R
import pl.ccki.szypwyp.presentation.base.BaseFragment
import pl.ccki.szypwyp.presentation.base.extensions.observe
import pl.ccki.szypwyp.presentation.base.extensions.permissionName
import pl.ccki.szypwyp.presentation.base.extensions.rxGetMap
import pl.ccki.szypwyp.presentation.databinding.FragmentMapBinding
import pl.ccki.szypwyp.presentation.dialogs.PermissionBlockedDialog
import pl.ccki.szypwyp.presentation.interfaces.MapViewsProvider
import pl.ccki.szypwyp.presentation.map.models.LocationMode
import pl.ccki.szypwyp.presentation.map.models.MapEvent
import pl.ccki.szypwyp.presentation.map.vm.MapViewModel
import javax.inject.Inject

class MapFragment : BaseFragment<FragmentMapBinding, MapViewModel>() {

    override val layoutId = R.layout.fragment_map
    override val viewModelClass = MapViewModel::class

    @Inject
    lateinit var mapViewsProvider: InjectableMap<PluginId, MapViewsProvider<MarkerModel>>

    override fun init(savedInstanceState: Bundle?) {
        savedInstanceState ?: viewModel.onFirstRun()
    }

    override fun initView(savedInstanceState: Bundle?) {
        initMap(savedInstanceState)
        viewModel.navigation
            .filter { it is MapEvent.MyLocationPermissionError }
            .flatMap {
                RxPermissions(this).requestEach(Permission.Location.permissionName)
            }
            .subscribe {
                when {
                    it.granted -> viewModel.onMyLocationClicked()
                    !it.shouldShowRequestPermissionRationale -> context?.let { context ->
                        PermissionBlockedDialog(
                            Permission.Location,
                            context,
                            this::startActivity
                        ).show()
                    }
                }
            }
            .disposeIn(disposeBag)

        viewModel.locationMode.observe(this) {
            binding.btnLocation.animate().rotation(when (it) {
                LocationMode.ContinuousUpdates -> 90f
                LocationMode.None, null -> 0f
                LocationMode.ZoomedUpdates -> 180f
            })
        }
        // navController.navigate(R.id.action_mapFragment_to_configurationFragment)
    }

    private fun initMap(savedInstanceState: Bundle?) {
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
            ?: return
        mapFragment.rxGetMap()
            .map { map ->
                context?.let {
                    SzypMap(
                        context = it,
                        googleMap = map,
                        viewModel = viewModel,
                        lifecycleOwner = this,
                        savedInstanceState = savedInstanceState,
                        viewsProvider = mapViewsProvider,
                        disposeBag = disposeBag
                    )
                }
            }
            .subscribe()
            .disposeIn(disposeBag)
    }
}
