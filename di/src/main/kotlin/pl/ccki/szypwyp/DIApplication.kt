package pl.ccki.szypwyp

import android.os.Build
import android.os.StrictMode
import com.google.firebase.FirebaseApp
import com.instabug.bug.BugReporting
import com.instabug.bug.invocation.InvocationOption
import com.instabug.library.Feature
import com.instabug.library.Instabug
import com.instabug.library.invocation.InstabugInvocationEvent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import io.reactivex.plugins.RxJavaPlugins
import pl.ccki.szypwyp.blinkee.di.DaggerBlinkeeComponent
import pl.ccki.szypwyp.di.DaggerApplicationComponent
import pl.ccki.szypwyp.goscooter.di.DaggerGoScooterComponent
import pl.ccki.szypwyp.platform.DaggerPlatformComponent
import pl.ccki.szypwyp.traficar.di.DaggerTraficarComponent
import pl.ccki.szypwyp.vozilla.di.DaggerVozillaComponent
import timber.log.Timber
import timber.log.Tree
import timber.log.debug
import timber.log.error
import javax.inject.Inject

class DIApplication : DaggerApplication() {

    @Inject
    lateinit var loggers: Set<@JvmSuppressWildcards Tree>

    override fun onCreate() {
        super.onCreate()
        Timber.plant(*loggers.toTypedArray())
        RxJavaPlugins.setErrorHandler {
            Timber.error(it) {
                "RxJavaError"
            }
        }
        FirebaseApp.initializeApp(this)!!
        if (BuildConfig.DEBUG) {
            StrictMode.ThreadPolicy.Builder().detectAll().build().let {
                StrictMode.setThreadPolicy(it)
            }
            StrictMode.VmPolicy.Builder().apply {
                detectLeakedSqlLiteObjects()
                detectActivityLeaks()
                detectLeakedClosableObjects()
                detectLeakedRegistrationObjects()
                detectFileUriExposure()
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    detectContentUriWithoutPermission()
                }
            }.build().let {
                StrictMode.setVmPolicy(it)
            }
        }
        Timber.debug { "firebase initalized" }
        Instabug.Builder(this, BuildConfig.INSTABUG_KEY).apply {
            setCrashReportingState(Feature.State.DISABLED)
        }.build()
        BugReporting.setInvocationOptions(InvocationOption.EMAIL_FIELD_OPTIONAL)
        Timber.debug { "instabug initialized" }
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
