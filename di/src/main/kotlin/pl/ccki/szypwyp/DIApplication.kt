package pl.ccki.szypwyp

import android.os.StrictMode
import com.google.firebase.FirebaseApp
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import io.reactivex.plugins.RxJavaPlugins
import pl.ccki.szypwyp.blinkee.di.DaggerBlinkeeComponent
import pl.ccki.szypwyp.di.DaggerApplicationComponent
import pl.ccki.szypwyp.goscooter.di.DaggerGoScooterComponent
import pl.ccki.szypwyp.platform.DaggerPlatformComponent
import pl.ccki.szypwyp.presentation.PresentationApplication
import pl.ccki.szypwyp.traficar.di.DaggerTraficarComponent
import pl.ccki.szypwyp.vozilla.di.DaggerVozillaComponent
import timber.log.LogcatTree
import timber.log.Timber
import timber.log.error

class DIApplication : PresentationApplication() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(LogcatTree())
        }
        RxJavaPlugins.setErrorHandler {
            Timber.error(it) {
                "RxJavaError: ${it.message.orEmpty()}"
            }
        }
        FirebaseApp.initializeApp(this)!!
        if (BuildConfig.DEBUG) {
            StrictMode.ThreadPolicy.Builder().detectAll().build().let {
                StrictMode.setThreadPolicy(it)
            }
            StrictMode.VmPolicy.Builder().detectAll().build().let {
                StrictMode.setVmPolicy(it)
            }
        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerApplicationComponent.builder()
            .blinkee(DaggerBlinkeeComponent.create())
            .vozilla(DaggerVozillaComponent.create())
            .goScooter(DaggerGoScooterComponent.create())
            .traficar(DaggerTraficarComponent.create())
            .platform(DaggerPlatformComponent.builder().apply {
                context(this@DIApplication)
                isDebug(BuildConfig.DEBUG)
            }.build())
            .create(this)
}
