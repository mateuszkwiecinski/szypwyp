package pl.ccki.szypwyp.presentation

import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import pl.ccki.szypwyp.presentation.base.BaseActivity
import pl.ccki.szypwyp.presentation.databinding.ActivityMainBinding
import pl.ccki.szypwyp.presentation.R

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val layoutId = R.layout.activity_main

    override fun init(savedInstanceState: Bundle?) = Unit

    override fun initView(savedInstanceState: Bundle?) {
        val navController = Navigation.findNavController(this, R.id.navController)
        binding.navBottomBar.setupWithNavController(navController)
        binding.navBottomBar.setOnNavigationItemReselectedListener {  }
    }
}
