package pl.ccki.szypwyp.presentation.configuration.ui

import android.os.Bundle
import android.util.TypedValue
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import com.instabug.bug.BugReporting
import pl.ccki.szypwyp.domain.base.disposeIn
import pl.ccki.szypwyp.presentation.R
import pl.ccki.szypwyp.presentation.base.BaseFragment
import pl.ccki.szypwyp.presentation.configuration.vm.ConfigurationViewModel
import pl.ccki.szypwyp.presentation.configuration.vm.NavigationEvent
import pl.ccki.szypwyp.presentation.databinding.FragmentConfigurationBinding
import timber.log.Timber
import timber.log.debug

class ConfigurationFragment : BaseFragment<FragmentConfigurationBinding, ConfigurationViewModel>() {
    override val layoutId: Int = R.layout.fragment_configuration
    override val viewModelClass = ConfigurationViewModel::class

    private val animationShort
        get() = resources.getInteger(android.R.integer.config_shortAnimTime).toLong()

    override fun init(savedInstanceState: Bundle?) {
        viewModel.navigation.subscribe {
            when (it) {
                NavigationEvent.ReportABug -> BugReporting.invoke()
                NavigationEvent.Licenses -> {
                    navController.navigate(R.id.action_configurationFragment_to_licensesFragment)
                }
                NavigationEvent.About -> {

                }
                null -> Unit
            }
        }.disposeIn(disposeBag)
    }

    override fun initView(savedInstanceState: Bundle?) {
        binding.toolbar.setNavigationOnClickListener { activity?.onBackPressed() }

        animateToolbarIn()
    }

    override fun onDestroyView() {
        animateToolbarOut()
        super.onDestroyView()
    }

    private fun animateToolbarIn() {
        val tv = TypedValue()
        if (activity?.theme?.resolveAttribute(android.R.attr.actionBarSize, tv, true) == true) {
            val height = TypedValue.complexToDimensionPixelSize(tv.data, resources.displayMetrics)
            binding.appBar.translationY = -height.toFloat()
            binding.appBar.animate()
                .apply {
                    startDelay = animationShort
                    translationY(0f)
                    duration = animationShort
                    interpolator = DecelerateInterpolator()
                }.start()
        }
    }

    private fun animateToolbarOut() {
        binding.appBar.animate()
            .apply {
                translationY(-binding.appBar.measuredHeight.toFloat())
                duration = animationShort
                interpolator = AccelerateInterpolator()
            }.start()
    }
}
