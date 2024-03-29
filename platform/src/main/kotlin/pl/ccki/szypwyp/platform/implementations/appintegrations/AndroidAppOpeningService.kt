package pl.ccki.szypwyp.platform.implementations.appintegrations

import android.content.ActivityNotFoundException
import android.content.Context
import pl.ccki.szypwyp.domain.models.AppId
import pl.ccki.szypwyp.domain.services.AppOpeningService
import javax.inject.Inject
import android.content.Intent
import android.net.Uri

class AndroidAppOpeningService @Inject constructor(
    private val context: Context
) : AppOpeningService {

    override fun openApp(appId: AppId) {
        with(context) {
            packageManager.getLaunchIntentForPackage(appId.id)?.let(this::startActivity)
        }
    }

    override fun openStore(appId: AppId) {
        try {
            "market://details?id=${appId.id}".startAsApp()
        } catch (exception: ActivityNotFoundException) {
            "https://play.google.com/store/apps/details?id=${appId.id}".startAsApp()
        }
    }

    private fun String.startAsApp() {
        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(this)))
    }
}
