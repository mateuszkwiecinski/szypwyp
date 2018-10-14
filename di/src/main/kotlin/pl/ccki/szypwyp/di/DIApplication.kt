package pl.ccki.szypwyp.di

import android.util.Log
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import io.reactivex.plugins.RxJavaPlugins
import pl.ccki.szypwyp.presentation.PresentationAplication
import pl.ccki.szypwyp.blinkee.di.DaggerBlinkeeComponent
import pl.ccki.szypwyp.vozilla.di.DaggerVozillaComponent
import pl.ccki.szypwyp.goscooter.di.DaggerGoScooterComponent
import pl.ccki.szypwyp.platform.DaggerPlatformComponent
import pl.ccki.szypwyp.traficar.di.DaggerTraficarComponent

class DIApplication : PresentationAplication() {

    override fun onCreate() {
        super.onCreate()
        RxJavaPlugins.setErrorHandler {
            Log.e("RxJavaError", it.message.orEmpty(), it)
        }
    }
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerApplicationComponent.builder()
            .blinkee(DaggerBlinkeeComponent.create())
            .vozilla(DaggerVozillaComponent.create())
            .goScooter(DaggerGoScooterComponent.create())
            .traficar(DaggerTraficarComponent.create())
            .platform(DaggerPlatformComponent.builder().context(this).build())
            .create(this)
}
