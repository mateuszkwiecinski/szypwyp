package pl.ccki.szypwyp.presentation.configuration.ui

import android.content.Intent
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.OvershootInterpolator
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import pl.ccki.szypwyp.domain.base.disposeIn
import pl.ccki.szypwyp.presentation.R
import pl.ccki.szypwyp.presentation.base.BaseFragment
import pl.ccki.szypwyp.presentation.configuration.vm.ConfigurationViewModel
import pl.ccki.szypwyp.presentation.configuration.vm.NavigationEvent
import pl.ccki.szypwyp.presentation.databinding.FragmentConfigurationBinding
import pl.ccki.szypwyp.presentation.dialogs.AppInfoDialog
import java.util.concurrent.TimeUnit

class ConfigurationFragment : BaseFragment<FragmentConfigurationBinding, ConfigurationViewModel>() {
    override val layoutId: Int = R.layout.fragment_configuration
    override val viewModelClass = ConfigurationViewModel::class

    private val animationDisposable = CompositeDisposable()

    override fun init(savedInstanceState: Bundle?) {
        viewModel.navigation.subscribe {
            when (it) {
                NavigationEvent.ReportABug -> sendEmail()
                NavigationEvent.Licenses -> navController.navigate(R.id.action_configurationFragment_to_licensesFragment)
                NavigationEvent.About -> context?.let(::AppInfoDialog)?.show()
                null -> Unit
            }
        }.disposeIn(disposeBag)
    }

    private fun sendEmail() {
        Intent(Intent.ACTION_SEND).apply {
            type = "plain/text";
            putExtra(Intent.EXTRA_EMAIL, arrayOf(getString(R.string.configuration_report_a_bug_email)))
            putExtra(Intent.EXTRA_SUBJECT, getString(R.string.configuration_report_a_bug_subject))
            putExtra(Intent.EXTRA_TEXT, "")
        }.let {
            context?.startActivity(Intent.createChooser(it, getString(R.string.configuration_report_a_bug_chooser_title)))
        }
    }

    override fun initView(savedInstanceState: Bundle?) {
        toolbarTitle = getString(R.string.toolbar_config)
        binding.imgLogo.setOnClickListener {
            animationDisposable.clear()
            binding.imgLogo.animate().apply {
                duration = 200
                interpolator = OvershootInterpolator(1.5f)
                scaleXBy(.3f)
                scaleYBy(.3f)
            }
            Observable.just(Unit).delay(5, TimeUnit.SECONDS).subscribe {
                binding.imgLogo.animate().apply {
                    duration = 500
                    interpolator = OvershootInterpolator(1.5f)
                    scaleX(1f)
                    scaleY(1f)
                }
            }.disposeIn(animationDisposable)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        animationDisposable.dispose()
    }
}
