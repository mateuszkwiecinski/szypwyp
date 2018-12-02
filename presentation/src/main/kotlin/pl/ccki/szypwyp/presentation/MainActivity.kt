package pl.ccki.szypwyp.presentation

import android.os.Bundle
import android.view.MenuItem
import androidx.constraintlayout.widget.ConstraintSet
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.transition.TransitionManager
import pl.ccki.szypwyp.presentation.base.BaseActivity
import pl.ccki.szypwyp.presentation.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val layoutId = R.layout.activity_main

    private val navController
        get() = Navigation.findNavController(this, R.id.navController)

    override fun init(savedInstanceState: Bundle?) = Unit

    override fun initView(savedInstanceState: Bundle?) {
        setupToolbar()
        setupNavigation()
    }

    private fun setupNavigation() {
        binding.navBottomBar.setOnNavigationItemReselectedListener { }
        binding.navBottomBar.setOnNavigationItemSelectedListener {
            val options = NavOptions.Builder()
                .apply {
                    setLaunchSingleTop(true)
                    setEnterAnim(androidx.navigation.ui.R.anim.nav_default_enter_anim)
                    setExitAnim(androidx.navigation.ui.R.anim.nav_default_exit_anim)
                    setPopEnterAnim(androidx.navigation.ui.R.anim.nav_default_pop_enter_anim)
                    setPopExitAnim(androidx.navigation.ui.R.anim.nav_default_pop_exit_anim)
                    setPopUpTo(navController.graph.startDestination, false)
                }.build()

            navController.navigate(it.itemId, null, options)
            true
        }
        navController.addOnNavigatedListener { _, destination ->
            when (destination.id) {
                R.id.fakeMapFragment -> animateToolbarOut()
                else -> animateToolbarIn()
            }
            binding.navBottomBar.menu.findItem(destination.id)?.isChecked = true
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun animateToolbarIn() {
        binding.let {
            ConstraintSet().apply {
                clone(it.constraintRoot)
                clear(it.appBar.id, ConstraintSet.BOTTOM)
                connect(it.appBar.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP)
                applyTo(it.constraintRoot)
            }

            TransitionManager.beginDelayedTransition(it.appBar)
        }
    }

    private fun animateToolbarOut() {
        binding.let {
            ConstraintSet().apply {
                clone(it.constraintRoot)
                clear(it.appBar.id, ConstraintSet.TOP)
                connect(it.appBar.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.TOP)
                applyTo(it.constraintRoot)
            }

            TransitionManager.beginDelayedTransition(it.appBar)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId){
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
