package pl.ccki.szypwyp.domain.services

import pl.ccki.szypwyp.domain.models.ExternalAppId

interface AppOpeningService {
    fun openApp(appId: ExternalAppId)

    fun openStore(appId: ExternalAppId)
}
