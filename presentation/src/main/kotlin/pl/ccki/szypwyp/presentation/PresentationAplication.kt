package pl.ccki.szypwyp.presentation

import com.crashlytics.android.core.CrashlyticsCore
import dagger.android.support.DaggerApplication
import io.fabric.sdk.android.Fabric

abstract class PresentationAplication : DaggerApplication() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Fabric.with(this, CrashlyticsCore.getInstance())
        }
    }
}
