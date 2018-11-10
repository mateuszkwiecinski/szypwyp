package pl.ccki.szypwyp.domain.commands

import io.reactivex.Completable
import pl.ccki.szypwyp.domain.base.Command
import pl.ccki.szypwyp.domain.base.SchedulersProvider
import pl.ccki.szypwyp.domain.base.applySchedulers
import pl.ccki.szypwyp.domain.providers.CurrentConfigProvider
import pl.ccki.szypwyp.domain.services.AppOpeningService
import javax.inject.Inject

class OpenStoreCommand @Inject constructor(
    private val currentConfigProvider: CurrentConfigProvider,
    private val appOpeningService: AppOpeningService,
    private val schedulersProvider: SchedulersProvider
) : Command<Unit> {

    override fun execute(param: Unit): Completable =
        Completable.fromAction {
            val appId = currentConfigProvider.appId
            appOpeningService.openStore(appId)
        }
            .applySchedulers(schedulersProvider)
}
