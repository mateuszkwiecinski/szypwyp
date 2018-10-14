package pl.ccki.szypwyp.presentation.map.ui

import android.os.Bundle
import com.google.android.gms.maps.SupportMapFragment
import pl.ccki.szypwyp.domain.base.disposeIn
import pl.ccki.szypwyp.presentation.R
import pl.ccki.szypwyp.presentation.base.BaseFragment
import pl.ccki.szypwyp.presentation.base.extensions.rxGetMap
import pl.ccki.szypwyp.presentation.databinding.FragmentMapBinding
import pl.ccki.szypwyp.presentation.map.vm.MapViewModel

class MapFragment : BaseFragment<FragmentMapBinding, MapViewModel>() {

    override val layoutId = R.layout.fragment_map
    override val viewModelClass = MapViewModel::class

    override fun init(savedInstanceState: Bundle?){
        savedInstanceState ?: viewModel.onFirstRun()
    }

    override fun initView(savedInstanceState: Bundle?) {
        initMap(savedInstanceState)
        // navController.navigate(R.id.action_mapFragment_to_configurationFragment)
    }

    private fun initMap(savedInstanceState: Bundle?) {
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment ?: return
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
