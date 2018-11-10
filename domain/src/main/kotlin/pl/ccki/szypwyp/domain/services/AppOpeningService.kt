package pl.ccki.szypwyp.domain.services

import pl.ccki.szypwyp.domain.models.AppId

interface AppOpeningService {
    fun openApp(appId: AppId)

    fun openStore(appId: AppId)
}
