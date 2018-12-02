package pl.ccki.szypwyp.vozilla.presentation.filters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.ccki.szypwyp.domain.models.FilterState
import pl.ccki.szypwyp.presentation.interfaces.FilterViewsProvider
import pl.ccki.szypwyp.vozilla.presentation.databinding.ViewVozillaFilterBinding
import javax.inject.Inject

class VozillaFilterViewsProvider @Inject constructor(
    private val viewModel: DetailedFiltersViewModel
) : FilterViewsProvider {

    override fun createViewHolder(inflater: LayoutInflater, parent: ViewGroup): RecyclerView.ViewHolder =
        VozillaHolder(ViewVozillaFilterBinding.inflate(inflater, parent, false))

    override fun bind(holder: RecyclerView.ViewHolder, state: FilterState, toggleFilter: (Boolean) -> Unit) {
        (holder as? VozillaHolder)?.also {
            it.binding.model = state.isEnabled
            it.binding.rootSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
                if (buttonView.isPressed) {
                    toggleFilter(isChecked)
                }
            }
            it.binding.filterList.adapter = VozillaDetailedFilterAdapter(viewModel)
        }
    }

    private data class VozillaHolder(val binding: ViewVozillaFilterBinding) : RecyclerView.ViewHolder(binding.root)
}
