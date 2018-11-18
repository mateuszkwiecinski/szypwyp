package pl.ccki.szypwyp.presentation.filters.ui

import android.os.Bundle
import pl.ccki.szypwyp.domain.base.InjectableMap
import pl.ccki.szypwyp.domain.models.PluginId
import pl.ccki.szypwyp.presentation.R
import pl.ccki.szypwyp.presentation.base.BaseFragment
import pl.ccki.szypwyp.presentation.databinding.FragmentFiltersBinding
import pl.ccki.szypwyp.presentation.filters.adapters.FilterAdapter
import pl.ccki.szypwyp.presentation.filters.vm.FiltersViewModel
import pl.ccki.szypwyp.presentation.interfaces.FilterViewsProvider
import javax.inject.Inject

class FiltersFragment : BaseFragment<FragmentFiltersBinding, FiltersViewModel>() {

    override val layoutId = R.layout.fragment_filters
    override val viewModelClass = FiltersViewModel::class

    @Inject
    lateinit var viewsProvider: InjectableMap<PluginId, FilterViewsProvider>

    override fun init(savedInstanceState: Bundle?) {
    }

    override fun initView(savedInstanceState: Bundle?) {
        binding.rvList.adapter = FilterAdapter(viewModel.filters, viewModel.diff, this, viewsProvider)
    }
}
