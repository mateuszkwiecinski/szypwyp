package pl.ccki.szypwyp.presentation.filters.vm

import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import pl.ccki.szypwyp.domain.base.disposeIn
import pl.ccki.szypwyp.domain.commands.UpdateFilterCommand
import pl.ccki.szypwyp.domain.models.PluginId
import pl.ccki.szypwyp.domain.queries.GetFiltersQuery
import pl.ccki.szypwyp.presentation.interfaces.base.BaseViewModel
import pl.ccki.szypwyp.presentation.interfaces.extensions.toLiveData
import pl.ccki.szypwyp.presentation.filters.diffs.FilterDiff
import javax.inject.Inject

class FiltersViewModel @Inject constructor(
    filtersQuery: GetFiltersQuery,
    private val updateFilter: UpdateFilterCommand
) : BaseViewModel() {

    private val dataStream = filtersQuery.execute()
        .share()

    val filters = dataStream.toLiveData(disposeBag)
    val diff: LiveData<DiffUtil.DiffResult>

    init {
        val oldSource = dataStream.startWith(emptyList<GetFiltersQuery.Item>())
        val newSource = dataStream.observeOn(Schedulers.computation())
        val zipper = BiFunction<List<GetFiltersQuery.Item>, List<GetFiltersQuery.Item>, DiffUtil.DiffResult> { old, new ->
            DiffUtil.calculateDiff(FilterDiff(old, new))
        }
        diff = Observable.zip(oldSource, newSource, zipper)
            .toLiveData(disposeBag)
    }

    fun onUserSelect(pluginId: PluginId, isChecked: Boolean) {
        updateFilter.execute(UpdateFilterCommand.Param(pluginId, isChecked))
            .subscribe()
            .disposeIn(disposeBag)
    }
}
