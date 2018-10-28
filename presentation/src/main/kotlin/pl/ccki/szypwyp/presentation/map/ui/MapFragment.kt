package pl.ccki.szypwyp.presentation.map.ui

import android.os.Bundle
import com.google.android.gms.maps.SupportMapFragment
import com.tbruyelle.rxpermissions2.RxPermissions
import pl.ccki.szypwyp.domain.base.disposeIn
import pl.ccki.szypwyp.domain.models.Permission
import pl.ccki.szypwyp.presentation.R
import pl.ccki.szypwyp.presentation.base.BaseFragment
import pl.ccki.szypwyp.presentation.base.extensions.observe
import pl.ccki.szypwyp.presentation.base.extensions.permissionName
import pl.ccki.szypwyp.presentation.base.extensions.rxGetMap
import pl.ccki.szypwyp.presentation.databinding.FragmentMapBinding
import pl.ccki.szypwyp.presentation.map.models.LocationMode
import pl.ccki.szypwyp.presentation.map.models.MapEvent
import pl.ccki.szypwyp.presentation.map.vm.MapViewModel

class MapFragment : BaseFragment<FragmentMapBinding, MapViewModel>() {

    override val layoutId = R.layout.fragment_map
    override val viewModelClass = MapViewModel::class

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
                    !it.shouldShowRequestPermissionRationale -> Unit //TODO show dialog, open settings
                }
            }
            .disposeIn(disposeBag)

        viewModel.locationMode.observe(this) {
            binding.btnLocation.animate().rotation(when(it){
                LocationMode.ContinuousUpdates -> 90f
                LocationMode.None, null -> 0f
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
                    SzypMap(it, map, viewModel, this, savedInstanceState)
                }
            }
            .subscribe()
            .disposeIn(disposeBag)
    }
}
