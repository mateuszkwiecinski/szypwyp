package pl.ccki.szypwyp.platform.implementations

import android.content.Context
import pl.ccki.szypwyp.domain.models.ExternalAppId
import pl.ccki.szypwyp.domain.services.AppsCheckingService
import javax.inject.Inject

class AndroidAppsCheckingService @Inject constructor(
    private val context: Context
) : AppsCheckingService {

    override fun isAppInstalled(appId: ExternalAppId): Boolean =
        context.packageManager.getLaunchIntentForPackage(appId.id) != null
}
