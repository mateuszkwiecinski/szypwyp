package pl.ccki.szypwyp.presentation

import android.os.Bundle
import android.util.Log
import io.reactivex.schedulers.Schedulers
import pl.ccki.szypwyp.domain.GetVehiclesCommand
import pl.ccki.szypwyp.domain.RefreshVehiclesQuery
import pl.ccki.szypwyp.domain.base.disposeIn
import pl.ccki.szypwyp.domain.base.run
import pl.ccki.szypwyp.presentation.base.BaseFragment
import pl.ccki.szypwyp.presentation.databinding.FragmentMapBinding
import javax.inject.Inject

class MapFragment : BaseFragment<FragmentMapBinding>() {

    override val layoutId = R.layout.fragment_map

    @Inject
    lateinit var refresh: RefreshVehiclesQuery

    @Inject
    lateinit var getVehicles: GetVehiclesCommand

    override fun init(savedInstanceState: Bundle?) = Unit

    override fun initView(savedInstanceState: Bundle?) {
        binding.btnClick.setOnClickListener {
            refresh.run()
                .subscribeOn(Schedulers.io())
                .subscribe()
                .disposeIn(disposeBag)
        }
        binding.btnClick.performClick()
        getVehicles.run()
            .subscribeOn(Schedulers.io())
            .subscribe {
                Log.d("Dupa", "diala ${it.size}")
            }
            .disposeIn(disposeBag)
        // navController.navigate(R.id.action_mapFragment_to_configurationFragment)
    }
}
