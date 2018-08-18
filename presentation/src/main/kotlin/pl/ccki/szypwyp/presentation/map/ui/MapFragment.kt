package pl.ccki.szypwyp.presentation.map.ui

import android.os.Bundle
import pl.ccki.szypwyp.presentation.R
import pl.ccki.szypwyp.presentation.base.BaseFragment
import pl.ccki.szypwyp.presentation.databinding.FragmentMapBinding
import pl.ccki.szypwyp.presentation.map.vm.MapViewModel

class MapFragment : BaseFragment<FragmentMapBinding, MapViewModel>() {

    override val layoutId = R.layout.fragment_map
    override val viewModelClass = MapViewModel::class

    override fun init(savedInstanceState: Bundle?) = Unit

    override fun initView(savedInstanceState: Bundle?) {
        // navController.navigate(R.id.action_mapFragment_to_configurationFragment)
    }
}
