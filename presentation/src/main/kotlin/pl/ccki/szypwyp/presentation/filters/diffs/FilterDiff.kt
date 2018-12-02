package pl.ccki.szypwyp.presentation.filters.diffs

import androidx.recyclerview.widget.DiffUtil
import pl.ccki.szypwyp.domain.queries.GetFiltersQuery

class FilterDiff(
    private val old: List<GetFiltersQuery.Item>,
    private val new: List<GetFiltersQuery.Item>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        old[oldItemPosition].pluginId == new[newItemPosition].pluginId

    override fun getOldListSize(): Int = old.size

    override fun getNewListSize(): Int = new.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        old[oldItemPosition] == new[newItemPosition]
}
