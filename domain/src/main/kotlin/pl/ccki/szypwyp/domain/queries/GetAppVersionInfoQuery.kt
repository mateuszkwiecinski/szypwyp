package pl.ccki.szypwyp.domain.queries

import io.reactivex.Observable
import pl.ccki.szypwyp.domain.base.Query
import pl.ccki.szypwyp.domain.base.SchedulersProvider
import pl.ccki.szypwyp.domain.base.applySchedulers
import pl.ccki.szypwyp.domain.models.CurrentAppVersionState
import pl.ccki.szypwyp.domain.models.compareTo
import pl.ccki.szypwyp.domain.providers.CurrentConfigProvider
import pl.ccki.szypwyp.domain.providers.RemoteConfigProvider
import javax.inject.Inject

class GetAppVersionInfoQuery @Inject constructor(
    private val remoteConfigProvider: RemoteConfigProvider,
    private val currentConfigProvider: CurrentConfigProvider,
    private val schedulersProvider: SchedulersProvider
) : Query<CurrentAppVersionState> {

    override fun execute(): Observable<CurrentAppVersionState> =
        remoteConfigProvider.versions
            .map {
                val current = currentConfigProvider.appVersion
                when {
                    it.minimum > current -> CurrentAppVersionState.Blocked
                    it.latest > current -> CurrentAppVersionState.NewerVersionAvailable
                    else -> CurrentAppVersionState.ItIsJustOkAndFineThanks
                }
            }
            .applySchedulers(schedulersProvider)
}
