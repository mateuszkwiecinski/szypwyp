package pl.ccki.szypwyp.di

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import io.reactivex.plugins.RxJavaPlugins
import pl.ccki.szypwyp.presentation.PresentationAplication
import pl.ccki.szypwyp.blinkee.di.DaggerBlinkeeComponent
import pl.ccki.szypwyp.vozilla.di.DaggerVozillaComponent
import pl.ccki.szypwyp.goscooter.di.DaggerGoScooterComponent
import pl.ccki.szypwyp.platform.DaggerPlatformComponent
import pl.ccki.szypwyp.traficar.di.DaggerTraficarComponent
import timber.log.LogcatTree
import timber.log.Timber
import timber.log.error

class DIApplication : PresentationAplication() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(LogcatTree())
        RxJavaPlugins.setErrorHandler {
            Timber.error(it){
                "RxJavaError: ${it.message.orEmpty()}"
            }
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
