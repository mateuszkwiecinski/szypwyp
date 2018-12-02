package pl.ccki.szypwyp.platform.implementations.remoteconfig

import com.google.android.gms.tasks.Tasks
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.google.gson.Gson
import com.google.gson.JsonParseException
import pl.ccki.szypwyp.domain.models.VersionsModel
import pl.ccki.szypwyp.platform.PlatformSingleton
import pl.ccki.szypwyp.platform.implementations.remoteconfig.models.VersionsResponse
import timber.log.Timber
import timber.log.error
import java.util.concurrent.ExecutionException
import javax.inject.Inject

@PlatformSingleton
class FirebaseRemoteConfig @Inject constructor(
    private val isDebug: Boolean
) {
    companion object {
        private const val VERSIONS_KEY = "version"
    }

    private val firebase by lazy {
        FirebaseRemoteConfig.getInstance().also {
            it.setConfigSettings(FirebaseRemoteConfigSettings.Builder().apply {
                setDeveloperModeEnabled(isDebug)
            }.build())
        }
    }

    val versions: VersionsModel?
        get() {
            try {
                Tasks.await(if (isDebug) {
                    firebase.fetch(0)
                } else {
                    firebase.fetch()
                })
                firebase.activateFetched()
            } catch (ex: InterruptedException) {
                //shhh
            } catch (ex: ExecutionException) {
                //shhh
            }

            return firebase.getString(VERSIONS_KEY).fromJson()?.let {
                val minimum = it.minimum?.toSemanticVersion() ?: return@let null
                val latest = it.latest?.toSemanticVersion() ?: return@let null
                VersionsModel(
                    minimum = minimum,
                    latest = latest
                )
            }
        }
}

fun String?.fromJson(): VersionsResponse? =
    try {
        Gson().fromJson(this, VersionsResponse::class.java)
    } catch (e: JsonParseException) {
        Timber.error(e) { "Cannot parse $this" }
        null
    }
