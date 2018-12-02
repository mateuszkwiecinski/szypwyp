package pl.ccki.szypwyp.vozilla.domain

import io.reactivex.Observable
import pl.ccki.szypwyp.domain.base.Query
import pl.ccki.szypwyp.vozilla.domain.models.DetailedFilterItem
import pl.ccki.szypwyp.vozilla.domain.models.DetailedFilterModel
import javax.inject.Inject

class GetDetailedFiltersQuery @Inject constructor() : Query<List<DetailedFilterItem>> {

    override fun execute() =
        Observable.just(mapOf(
            DetailedFilterModel("van") to true,
            DetailedFilterModel("zwykle 1") to false,
            DetailedFilterModel("zwykle 2") to true,
            DetailedFilterModel("zwykle 3") to false
        ))
            .map {
                it.toList().map { DetailedFilterItem(it.first, it.second) }
            }
}
