package pl.ccki.szypwyp.domain.commands

import io.reactivex.Completable
import io.reactivex.Maybe
import pl.ccki.szypwyp.domain.base.Command
import pl.ccki.szypwyp.domain.base.InjectableMap
import pl.ccki.szypwyp.domain.models.AppId
import pl.ccki.szypwyp.domain.models.PluginId
import pl.ccki.szypwyp.domain.services.AppOpeningService
import pl.ccki.szypwyp.domain.services.AppsCheckingService
import pl.ccki.szypwyp.domain.services.ExternalPlugin
import javax.inject.Inject

class OpenExternalAppCommand @Inject constructor(
    private val supportedApps: InjectableMap<PluginId, ExternalPlugin>,
    private val appsChecker: AppsCheckingService,
    private val appOpener: AppOpeningService
) : Command<PluginId> {

    override fun execute(param: PluginId): Completable =
        Maybe.fromCallable<AppId> {
            supportedApps[param]?.appId
        }
            .doOnSuccess {
                if (appsChecker.isAppInstalled(it)) {
                    appOpener.openApp(it)
                } else {
                    appOpener.openStore(it)
                }
            }
            .ignoreElement()
}
