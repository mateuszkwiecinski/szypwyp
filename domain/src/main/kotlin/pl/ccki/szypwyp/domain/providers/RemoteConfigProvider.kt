package pl.ccki.szypwyp.domain.providers

import io.reactivex.Observable
import pl.ccki.szypwyp.domain.models.VersionsModel

interface RemoteConfigProvider {
    val versions: Observable<VersionsModel>
}
