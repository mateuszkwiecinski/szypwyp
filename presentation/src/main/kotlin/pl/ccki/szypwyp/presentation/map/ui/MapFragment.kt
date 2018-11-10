package pl.ccki.szypwyp.presentation.map.ui

import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation.INFINITE
import android.view.animation.Animation.RELATIVE_TO_SELF
import android.view.animation.RotateAnimation
import com.google.android.gms.maps.SupportMapFragment
import com.tapadoo.alerter.Alerter
import com.tbruyelle.rxpermissions2.RxPermissions
import pl.ccki.szypwyp.domain.base.InjectableMap
import pl.ccki.szypwyp.domain.base.disposeIn
import pl.ccki.szypwyp.domain.models.CurrentAppVersionState
import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.domain.models.Permission
import pl.ccki.szypwyp.domain.models.PluginId
import pl.ccki.szypwyp.presentation.R
import pl.ccki.szypwyp.presentation.base.BaseFragment
import pl.ccki.szypwyp.presentation.base.extensions.observe
import pl.ccki.szypwyp.presentation.base.extensions.permissionName
import pl.ccki.szypwyp.presentation.base.extensions.rxGetMap
import pl.ccki.szypwyp.presentation.databinding.FragmentMapBinding
import pl.ccki.szypwyp.presentation.dialogs.AppBlockedDialog
import pl.ccki.szypwyp.presentation.dialogs.PermissionBlockedDialog
import pl.ccki.szypwyp.presentation.interfaces.MapViewsProvider
import pl.ccki.szypwyp.presentation.map.listeners.AnimationListener
import pl.ccki.szypwyp.presentation.map.models.LocationMode
import pl.ccki.szypwyp.presentation.map.models.MapEvent
import pl.ccki.szypwyp.presentation.map.vm.MapViewModel
import javax.inject.Inject

class MapFragment : BaseFragment<FragmentMapBinding, MapViewModel>() {

    override val layoutId = R.layout.fragment_map
    override val viewModelClass = MapViewModel::class

    @Inject
    lateinit var mapViewsProvider: InjectableMap<PluginId, MapViewsProvider<MarkerModel>>

    override fun init(savedInstanceState: Bundle?) {
        savedInstanceState ?: viewModel.onFirstRun()
    }

    override fun initView(savedInstanceState: Bundle?) {
        initMap(savedInstanceState)
        viewModel.navigation
            .filter { it is MapEvent.MyLocationPermissionError }
            .flatMap {
                RxPermissions(this).requestEach(Permission.Location.permissionName)
            }
            .subscribe {
                when {
                    it.granted -> viewModel.onMyLocationClicked()
                    !it.shouldShowRequestPermissionRationale -> context?.let { context ->
                        PermissionBlockedDialog(
                            Permission.Location,
                            context,
                            this::startActivity
                        ).show()
                    }
                }
            }
            .disposeIn(disposeBag)

        viewModel.locationMode.observe(this) {
            binding.btnLocation.animate().rotation(when (it) {
                LocationMode.ContinuousUpdates -> 90f
                LocationMode.None, null -> 0f
                LocationMode.ZoomedUpdates -> 180f
            })
        }
        viewModel.canChangeTarget.observe(this) {
            if (it == true) {
                binding.btnChangeSearchTarget.animate().apply {
                    alpha(1f)
                    translationY(0f)
                    withStartAction {
                        binding.btnChangeSearchTarget.visibility = View.VISIBLE
                    }
                }.start()
            } else {
                binding.btnChangeSearchTarget.animate().apply {
                    alpha(0f)
                    translationY(-30f)
                    withEndAction {
                        binding.btnChangeSearchTarget.visibility = View.GONE
                    }
                }.start()
            }
        }
        viewModel.isLoadingSomething.observe(this) {
            if (it == true) {
                val animation = RotateAnimation(0f, 360f, RELATIVE_TO_SELF, .5f, RELATIVE_TO_SELF, .5f).apply {
                    repeatCount = INFINITE
                    duration = 700
                    interpolator = AccelerateDecelerateInterpolator()
                    fillAfter = true
                    setAnimationListener(AnimationListener(
                        onAnimationRepeat = { anim ->
                            if (viewModel.isLoadingSomething.value != true) {
                                anim.cancel()
                            }
                        }
                    ))
                }
                binding.btnRefresh.startAnimation(animation)
            }
        }

        viewModel.shouldShowError.observe(this) {
            val activity = activity ?: return@observe
            when (viewModel.appInfoState.value) {
                CurrentAppVersionState.ItIsJustOkAndFineThanks, null -> Unit
                CurrentAppVersionState.Blocked,
                CurrentAppVersionState.NewerVersionAvailable -> return@observe
                //FIXME HACK: this behavior will be obsolete when showing inline errors is introduced
            }
            if (it == true) {
                Alerter.create(activity)
                    .apply {
                        setTitle(R.string.map_error_loading_title)
                        setText(R.string.map_error_loading_message)
                        setBackgroundColorRes(R.color.md_amber_900)
                        setIcon(R.drawable.ic_info)
                        enableInfiniteDuration(true)
                    }
                    .show()
            } else {
                Alerter.clearCurrent(activity)
            }
        }
        viewModel.appInfoState.observe(this) {
            val activity = activity ?: return@observe
            when (it) {
                CurrentAppVersionState.Blocked -> AppBlockedDialog(activity) {
                    viewModel.onOpenStoreClicked()
                }.show()
                CurrentAppVersionState.NewerVersionAvailable -> {
                    Alerter.create(activity)
                        .apply {
                            setTitle(R.string.map_info_newer_version_title)
                            setText(R.string.map_info_newer_version_message)
                            setBackgroundColorRes(R.color.md_light_blue_400)
                            setIcon(R.drawable.ic_get_app)
                            enableInfiniteDuration(true)
                            addButton(getString(R.string.map_info_newer_version_negative), R.style.AlertButton, View.OnClickListener {
                                Alerter.hide()
                            })
                            addButton(getString(R.string.map_info_newer_version_positive), R.style.AlertButton, View.OnClickListener {
                                viewModel.onOpenStoreClicked()
                                Alerter.hide()
                            })
                        }
                        .show()
                }
                CurrentAppVersionState.ItIsJustOkAndFineThanks, null -> Unit
            }
        }
    }

    private fun initMap(savedInstanceState: Bundle?) {
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
            ?: return
        mapFragment.rxGetMap()
            .map { map ->
                context?.let {
                    SzypMap(
                        context = it,
                        googleMap = map,
                        viewModel = viewModel,
                        lifecycleOwner = this,
                        savedInstanceState = savedInstanceState,
                        viewsProvider = mapViewsProvider,
                        disposeBag = disposeBag
                    )
                }
            }
            .subscribe()
            .disposeIn(disposeBag)
    }
}
