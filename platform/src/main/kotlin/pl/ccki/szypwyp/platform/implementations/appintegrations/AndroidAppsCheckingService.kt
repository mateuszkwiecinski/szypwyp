package pl.ccki.szypwyp.platform.implementations.appintegrations

import android.content.Context
import pl.ccki.szypwyp.domain.models.AppId
import pl.ccki.szypwyp.domain.services.AppsCheckingService
import javax.inject.Inject

class AndroidAppsCheckingService @Inject constructor(
    private val context: Context
) : AppsCheckingService {

    override fun isAppInstalled(appId: AppId): Boolean =
        context.packageManager.getLaunchIntentForPackage(appId.id) != null
}
