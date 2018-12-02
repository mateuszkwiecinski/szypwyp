package pl.ccki.szypwyp.presentation.interfaces

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.ccki.szypwyp.domain.models.FilterState

interface FilterViewsProvider {
    fun createViewHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder
    fun bind(holder: RecyclerView.ViewHolder, state: FilterState, toggleFilter: (Boolean) -> Unit)
}
