package pl.ccki.szypwyp.presentation.map.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import pl.ccki.szypwyp.presentation.R

class FakeMapFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // HARD HACK Recreating the map takes way to much time. I decided to keep map hidden instead of replacing it
        activity?.supportFragmentManager?.let {
            it.beginTransaction().apply {
                setCustomAnimations(
                    androidx.navigation.ui.R.anim.nav_default_enter_anim,
                    androidx.navigation.ui.R.anim.nav_default_exit_anim,
                    androidx.navigation.ui.R.anim.nav_default_pop_enter_anim,
                    androidx.navigation.ui.R.anim.nav_default_pop_exit_anim
                )
                it.findFragmentById(R.id.actualMapFragment)?.let(this::show)
            }.commitAllowingStateLoss()
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onDestroyView() {
        activity?.supportFragmentManager?.let {
            it.beginTransaction().apply {
                setCustomAnimations(
                    androidx.navigation.ui.R.anim.nav_default_enter_anim,
                    androidx.navigation.ui.R.anim.nav_default_exit_anim,
                    androidx.navigation.ui.R.anim.nav_default_pop_enter_anim,
                    androidx.navigation.ui.R.anim.nav_default_pop_exit_anim
                )
                it.findFragmentById(R.id.actualMapFragment)?.let(this::hide)
            }.commitAllowingStateLoss()
        }
        super.onDestroyView()
    }
}
