package pl.ccki.szypwyp.platform.implementations.remoteconfig

import android.content.Context
import pl.ccki.szypwyp.domain.models.AppId
import pl.ccki.szypwyp.domain.models.SemanticVersionModel
import pl.ccki.szypwyp.domain.providers.CurrentConfigProvider
import javax.inject.Inject

class AndroidCurrentConfigProvider @Inject constructor(
    context: Context
) : CurrentConfigProvider {

    private val packageInfo by lazy {
        with(context) {
            packageManager.getPackageInfo(packageName, 0)
        }
    }
    override val appVersion: SemanticVersionModel by lazy {
        packageInfo.versionName.toSemanticVersion()
    }
    override val appId: AppId
        get() = AppId(packageInfo.packageName)
}
