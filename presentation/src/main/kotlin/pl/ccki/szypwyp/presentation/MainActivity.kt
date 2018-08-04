package pl.ccki.szypwyp.presentation

import android.os.Bundle
import pl.ccki.szypwyp.presentation.base.BaseActivity
import pl.ccki.szypwyp.presentation.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val layoutId = R.layout.activity_main

    override fun init(savedInstanceState: Bundle?) = Unit

    override fun initView(savedInstanceState: Bundle?) = Unit
}
