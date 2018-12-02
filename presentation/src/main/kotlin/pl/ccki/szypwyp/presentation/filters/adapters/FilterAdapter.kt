package pl.ccki.szypwyp.presentation.filters.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import pl.ccki.szypwyp.domain.base.InjectableMap
import pl.ccki.szypwyp.domain.models.PluginId
import pl.ccki.szypwyp.domain.queries.GetFiltersQuery
import pl.ccki.szypwyp.presentation.databinding.ItemFiltersDefaultBinding
import pl.ccki.szypwyp.presentation.interfaces.FilterViewsProvider
import pl.ccki.szypwyp.presentation.interfaces.extensions.observe

class FilterAdapter(
    private val source: LiveData<List<GetFiltersQuery.Item>>,
    diff: LiveData<DiffUtil.DiffResult>,
    lifecycleOwner: LifecycleOwner,
    viewProvider: InjectableMap<PluginId, FilterViewsProvider>,
    private val onItemSelected: (PluginId, Boolean) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items
        get() = source.value.orEmpty()

    private val viewTypesMap = viewProvider.toList().withIndex().map { (index, data) ->
        index to data.second
    }.toMap()

    private val viewTypesReversedMap = viewProvider.toList().withIndex().map { (index, data) ->
        data.first to index
    }.toMap().let {
        it.toMutableMap().also { it.clear() } // TODO: @mk 02/12/2018 remove this
    }

    init {
        diff.observe(lifecycleOwner) {
            it?.dispatchUpdatesTo(this) ?: notifyDataSetChanged()
        }
    }

    override fun getItemViewType(position: Int): Int =
        viewTypesReversedMap[items[position].pluginId] ?: -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val viewsProvider = viewTypesMap[viewType]

        return viewsProvider?.createViewHolder(inflater, parent)
            ?: Empty(ItemFiltersDefaultBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewsProvider = viewTypesMap[holder.itemViewType]

        viewsProvider?.bind(
            holder = holder,
            state = items[position].state,
            toggleFilter = { onItemSelected(items[position].pluginId, it) }
        ) ?: bindDefault(holder, position)
    }

    private fun bindDefault(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? Empty)?.also {
            it.binding.model = items[position]
            it.binding.rootSwitch.setOnCheckedChangeListener { view, isChecked ->
                if (view.isPressed) {
                    onItemSelected(items[position].pluginId, isChecked)
                }
            }
        }
    }

    private class Empty(val binding: ItemFiltersDefaultBinding) : RecyclerView.ViewHolder(binding.root)
}
