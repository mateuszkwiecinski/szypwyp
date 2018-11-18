package pl.ccki.szypwyp.domain.persistences

import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import pl.ccki.szypwyp.domain.models.PluginId
import pl.ccki.szypwyp.domain.repositories.FiltersRepository
import javax.inject.Inject

interface FiltersPersistence {
    fun disabled(): Maybe<Set<PluginId>>
    fun observeDisabled(): Observable<Set<PluginId>>
    fun updateDisabled(new: Set<PluginId>): Completable
}

class InMemoryFiltersPersistence @Inject constructor(
    private val repository: FiltersRepository
) : BehaviorSubjectBasedPersistence<Set<PluginId>>({ repository.disabled }), FiltersPersistence {

    override fun disabled(): Maybe<Set<PluginId>> = current()

    override fun observeDisabled(): Observable<Set<PluginId>> = get()

    override fun updateDisabled(new: Set<PluginId>): Completable = update(new)
}
