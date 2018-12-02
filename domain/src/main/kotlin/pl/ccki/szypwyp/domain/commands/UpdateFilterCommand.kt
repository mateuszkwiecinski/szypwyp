package pl.ccki.szypwyp.domain.commands

import io.reactivex.Completable
import pl.ccki.szypwyp.domain.base.Command
import pl.ccki.szypwyp.domain.base.SchedulersProvider
import pl.ccki.szypwyp.domain.base.applySchedulers
import pl.ccki.szypwyp.domain.models.PluginId
import pl.ccki.szypwyp.domain.persistences.FiltersPersistence
import pl.ccki.szypwyp.domain.repositories.FiltersRepository
import javax.inject.Inject

class UpdateFilterCommand @Inject constructor(
    private val repository: FiltersRepository,
    private val persistence: FiltersPersistence,
    private val schedulersProvider: SchedulersProvider
) : Command<UpdateFilterCommand.Param> {

    data class Param(val id: PluginId, val shouldEnable: Boolean)

    override fun execute(param: Param): Completable =
        persistence.disabled()
            .map {
                if (param.shouldEnable) {
                    it.minus(param.id)
                } else {
                    it.plus(param.id)
                }.toSet()
            }
            .doOnSuccess { repository.disabled = it }
            .flatMapCompletable(persistence::updateDisabled)
            .applySchedulers(schedulersProvider)
}
