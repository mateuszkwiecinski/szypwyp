package pl.ccki.szypwyp.vozilla.presentation.filters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.ccki.szypwyp.domain.models.FilterState
import pl.ccki.szypwyp.presentation.interfaces.FilterViewsProvider
import javax.inject.Inject

class VozillaFilterViewsProvider @Inject constructor(
) : FilterViewsProvider {
    override fun createViewHolder(inflater: LayoutInflater, parent: ViewGroup): RecyclerView.ViewHolder {
        return object : RecyclerView.ViewHolder(View(parent.context)) {}
    }

    override fun bind(holder: RecyclerView.ViewHolder, state: FilterState) {

    }
}
