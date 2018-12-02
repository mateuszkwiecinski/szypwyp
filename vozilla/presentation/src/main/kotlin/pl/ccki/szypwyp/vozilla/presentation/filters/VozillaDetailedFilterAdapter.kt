package pl.ccki.szypwyp.vozilla.presentation.filters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.ccki.szypwyp.vozilla.presentation.databinding.ViewVozillaFilterDetailedBinding

class VozillaDetailedFilterAdapter(viewModel: DetailedFiltersViewModel) : RecyclerView.Adapter<VozillaDetailedFilterHolder>() {

    val items = viewModel.items

    init {
        viewModel.diff.observeForever {
            it.dispatchUpdatesTo(this)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VozillaDetailedFilterHolder {
        val inflater = LayoutInflater.from(parent.context)
        return VozillaDetailedFilterHolder(ViewVozillaFilterDetailedBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int = items.value.orEmpty().size

    override fun onBindViewHolder(holder: VozillaDetailedFilterHolder, position: Int) {
        holder.binding.model = items.value?.getOrNull(position)
    }
}

data class VozillaDetailedFilterHolder(val binding: ViewVozillaFilterDetailedBinding) : RecyclerView.ViewHolder(binding.root)
