package pl.ccki.szypwyp.presentation.configuration.ui

import android.os.Bundle
import com.mikepenz.aboutlibraries.LibsBuilder
import pl.ccki.szypwyp.domain.base.TAG
import pl.ccki.szypwyp.presentation.R
import pl.ccki.szypwyp.presentation.base.BaseFragment
import pl.ccki.szypwyp.presentation.interfaces.base.BaseViewModel
import pl.ccki.szypwyp.presentation.databinding.FragmentLicensesBinding
import javax.inject.Inject

class LicensesFragment : BaseFragment<FragmentLicensesBinding, LicensesViewModel>() {

    override val layoutId = R.layout.fragment_licenses
    override val viewModelClass = LicensesViewModel::class

    override fun init(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            childFragmentManager.beginTransaction().apply {
                LibsBuilder().apply {
                    withAboutIconShown(true)
                    withAboutVersionShown(true)
                    withLicenseShown(true)
                    withLicenseDialog(true)
                    withLibraries("Alerter", "RxPermissions", "constraintlayout", "timber", "rxjava", "rxandroid", "retrofit",
                        "okhttp", "jsoup", "gson", "googleplayservices", "dagger2", "android_maps_utils")
                }.supportFragment().let {
                    replace(R.id.container, it, it.TAG)
                }
            }.commit()
        }
    }

    override fun initView(savedInstanceState: Bundle?) = Unit
}

class LicensesViewModel @Inject constructor() : BaseViewModel()
