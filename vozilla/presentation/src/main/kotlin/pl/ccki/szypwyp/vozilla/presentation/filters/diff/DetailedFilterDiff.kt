package pl.ccki.szypwyp.vozilla.presentation.filters.diff

import androidx.recyclerview.widget.DiffUtil
import pl.ccki.szypwyp.vozilla.domain.models.DetailedFilterItem

class DetailedFilterDiff(
    private val old: List<DetailedFilterItem>,
    private val new: List<DetailedFilterItem>
) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        old[oldItemPosition].filter == new[newItemPosition].filter

    override fun getOldListSize(): Int = old.size

    override fun getNewListSize(): Int = new.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        old[oldItemPosition] == new[newItemPosition]

}
