package pl.ccki.szypwyp.presentation.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import io.reactivex.disposables.CompositeDisposable
import pl.ccki.szypwyp.presentation.BR
import pl.ccki.szypwyp.presentation.interfaces.base.BaseViewModel
import javax.inject.Inject
import kotlin.reflect.KClass

abstract class BaseFragment<TBinding : ViewDataBinding, TViewModel : BaseViewModel> : Fragment(), HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var viewModelFactory: ViewModelsFactory

    lateinit var viewModel: TViewModel

    protected abstract val layoutId: Int
    protected abstract val viewModelClass: KClass<TViewModel>
    protected lateinit var binding: TBinding

    protected var toolbarTitle: String?
        get() = (activity as? AppCompatActivity)?.supportActionBar?.title?.toString()
        set(value) {
            (activity as? AppCompatActivity)?.supportActionBar?.title = value
        }

    protected val navController: NavController
        get() = Navigation.findNavController(binding.root)

    protected val disposeBag = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)[viewModelClass.java]
        lifecycle.addObserver(viewModel)
        init(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.setLifecycleOwner(this)
        binding.setVariable(BR.model, viewModel)
        initView(savedInstanceState)

        return binding.root
    }

    abstract fun init(savedInstanceState: Bundle?)

    abstract fun initView(savedInstanceState: Bundle?)

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector

    override fun onDestroy() {
        disposeBag.dispose()
        lifecycle.removeObserver(viewModel)
        super.onDestroy()
    }
}
