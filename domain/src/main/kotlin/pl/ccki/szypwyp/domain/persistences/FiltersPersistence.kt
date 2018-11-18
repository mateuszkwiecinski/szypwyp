package pl.ccki.szypwyp.domain.persistences

import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import pl.ccki.szypwyp.domain.models.PluginId
import pl.ccki.szypwyp.domain.repositories.FiltersRepository
import javax.inject.Inject

interface FiltersPersistence {
    fun current(): Maybe<Iterable<PluginId>>
    fun observeDisabled(): Observable<Iterable<PluginId>>
    fun updateDisabled(new: Iterable<PluginId>): Completable
}

class InMemoryFiltersPersistence @Inject constructor(
    private val repository: FiltersRepository
) : BehaviorSubjectBasedPersistence<Iterable<PluginId>>({ repository.selected }), FiltersPersistence {

    override fun observeDisabled(): Observable<Iterable<PluginId>> = get()

    override fun updateDisabled(new: Iterable<PluginId>): Completable = update(new)

}
