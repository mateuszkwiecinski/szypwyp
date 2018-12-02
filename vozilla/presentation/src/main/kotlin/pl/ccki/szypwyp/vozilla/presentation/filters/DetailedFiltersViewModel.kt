package pl.ccki.szypwyp.vozilla.presentation.filters

import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import pl.ccki.szypwyp.presentation.interfaces.base.BaseViewModel
import pl.ccki.szypwyp.presentation.interfaces.extensions.toLiveData
import pl.ccki.szypwyp.vozilla.domain.GetDetailedFiltersQuery
import pl.ccki.szypwyp.vozilla.domain.models.DetailedFilterItem
import pl.ccki.szypwyp.vozilla.presentation.filters.diff.DetailedFilterDiff
import javax.inject.Inject

class DetailedFiltersViewModel @Inject constructor(
    detailedFiltersQuery: GetDetailedFiltersQuery
) : BaseViewModel() {

    private val dataStream = detailedFiltersQuery.execute()
        .share()

    val items = dataStream.toLiveData(disposeBag)
    val diff: LiveData<DiffUtil.DiffResult>

    init {
        val oldSource = dataStream.startWith(emptyList<DetailedFilterItem>())
        val newSource = dataStream.observeOn(Schedulers.computation())
        val zipper = BiFunction<List<DetailedFilterItem>, List<DetailedFilterItem>, DiffUtil.DiffResult> { old, new ->
            DiffUtil.calculateDiff(DetailedFilterDiff(old, new))
        }
        diff = Observable.zip(oldSource, newSource, zipper)
            .toLiveData(disposeBag)
    }
}
