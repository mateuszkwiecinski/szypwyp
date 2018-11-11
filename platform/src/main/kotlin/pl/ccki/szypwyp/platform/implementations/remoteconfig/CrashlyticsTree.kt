package pl.ccki.szypwyp.platform.implementations.remoteconfig

import com.crashlytics.android.Crashlytics
import timber.log.Tree

class CrashlyticsTree : Tree() {

    override fun performLog(priority: Int, tag: String?, throwable: Throwable?, message: String?) {
        message?.let { Crashlytics.log("$tag($priority): $message") }
        throwable?.let { Crashlytics.logException(it) }
    }
}
