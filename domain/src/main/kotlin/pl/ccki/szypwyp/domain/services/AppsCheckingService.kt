package pl.ccki.szypwyp.domain.services

import pl.ccki.szypwyp.domain.models.ExternalAppId

interface AppsCheckingService {
    fun isAppInstalled(appId: ExternalAppId): Boolean
}
