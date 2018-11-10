package pl.ccki.szypwyp.platform.implementations.remoteconfig

import io.reactivex.Observable
import pl.ccki.szypwyp.domain.models.VersionsModel
import pl.ccki.szypwyp.domain.providers.RemoteConfigProvider
import pl.ccki.szypwyp.platform.PlatformSingleton
import javax.inject.Inject

@PlatformSingleton
class FirebaseRemoteConfigRxWrapper @Inject constructor(
    private val firebase: FirebaseRemoteConfig
) : RemoteConfigProvider {

    override val versions = Observable.fromCallable<VersionsModel> {
        firebase.versions
    }.share()
}
