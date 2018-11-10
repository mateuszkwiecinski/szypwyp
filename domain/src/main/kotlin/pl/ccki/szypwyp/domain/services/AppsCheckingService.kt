package pl.ccki.szypwyp.domain.services

import pl.ccki.szypwyp.domain.models.AppId

interface AppsCheckingService {
    fun isAppInstalled(appId: AppId): Boolean
}
