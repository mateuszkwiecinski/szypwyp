package pl.ccki.szypwyp.di

import android.util.Log
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import io.reactivex.plugins.RxJavaPlugins
import pl.ccki.szypwyp.blinkee.BlinkeeComponent
import pl.ccki.szypwyp.blinkee.DaggerBlinkeeComponent

class DIApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        RxJavaPlugins.setErrorHandler {
            Log.e("RxJavaError", it.message.orEmpty(), it)
        }
    }
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerApplicationComponent.builder()
            .blinkee(DaggerBlinkeeComponent.builder().build()).create(this)
}