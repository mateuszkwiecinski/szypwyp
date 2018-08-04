package pl.ccki.szypwyp.presentation

import android.os.Bundle
import pl.ccki.szypwyp.presentation.base.BaseFragment
import pl.ccki.szypwyp.presentation.databinding.FragmentConfigurationBinding

class ConfigurationFragment : BaseFragment<FragmentConfigurationBinding>() {
    override val layoutId: Int = R.layout.fragment_configuration

    override fun init(savedInstanceState: Bundle?) = Unit
    override fun initView(savedInstanceState: Bundle?) = Unit
}
