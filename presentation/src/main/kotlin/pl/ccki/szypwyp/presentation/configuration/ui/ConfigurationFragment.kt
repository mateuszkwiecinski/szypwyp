package pl.ccki.szypwyp.presentation.configuration.ui

import android.os.Bundle
import pl.ccki.szypwyp.presentation.R
import pl.ccki.szypwyp.presentation.base.BaseFragment
import pl.ccki.szypwyp.presentation.configuration.vm.ConfigurationViewModel
import pl.ccki.szypwyp.presentation.databinding.FragmentConfigurationBinding

class ConfigurationFragment : BaseFragment<FragmentConfigurationBinding, ConfigurationViewModel>() {
    override val layoutId: Int = R.layout.fragment_configuration
    override val viewModelClass = ConfigurationViewModel::class

    override fun init(savedInstanceState: Bundle?) = Unit
    override fun initView(savedInstanceState: Bundle?) = Unit
}
