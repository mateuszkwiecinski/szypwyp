package pl.ccki.szypwyp.presentation.filters.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import pl.ccki.szypwyp.domain.base.InjectableMap
import pl.ccki.szypwyp.domain.models.PluginId
import pl.ccki.szypwyp.domain.queries.GetFiltersQuery
import pl.ccki.szypwyp.presentation.base.extensions.observe
import pl.ccki.szypwyp.presentation.interfaces.FilterViewsProvider
import java.util.UUID

class FilterAdapter(
    private val source: LiveData<List<GetFiltersQuery.Item>>,
    diff: LiveData<DiffUtil.DiffResult>,
    lifecycleOwner: LifecycleOwner,
    viewProvider: InjectableMap<PluginId, FilterViewsProvider>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items
        get() = source.value.orEmpty()

    private val viewTypesReverMap = viewProvider.toList().withIndex().map { (index, data) ->
        index to data.second
    }.toMap()

    init {
        diff.observe(lifecycleOwner) {
            it?.dispatchUpdatesTo(this) ?: notifyDataSetChanged()
        }
    }

    override fun getItemViewType(position: Int): Int =
        items[position].pluginId.hashCode()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val viewsProvider = viewTypesReverMap[viewType]

        return viewsProvider?.createViewHolder(inflater, parent) ?: Empty(parent.context)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewsProvider = viewTypesReverMap[holder.itemViewType]

        viewsProvider?.bind(holder, items[position].state)
    }

    private class Empty(context: Context) : RecyclerView.ViewHolder(View(context))

}
