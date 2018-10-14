package pl.ccki.szypwyp.domain.queries

import io.reactivex.Completable
import io.reactivex.Single
import pl.ccki.szypwyp.domain.base.Query
import pl.ccki.szypwyp.domain.base.SchedulersProvider
import pl.ccki.szypwyp.domain.base.applySchedulers
import pl.ccki.szypwyp.domain.models.Camera
import pl.ccki.szypwyp.domain.models.DEFAULT_LOCATION
import pl.ccki.szypwyp.domain.models.Zoom
import pl.ccki.szypwyp.domain.persistences.CameraPersistence
import pl.ccki.szypwyp.domain.providers.LocationProvider
import pl.ccki.szypwyp.domain.repositories.SearchConfigRepository
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class FindFirstCameraQuery @Inject constructor(
    private val cameraPersistence: CameraPersistence,
    private val locationProvider: LocationProvider,
    private val searchConfig: SearchConfigRepository,
    private val schedulersProvider: SchedulersProvider
) : Query<Unit> {

    override fun execute(param: Unit): Completable =
        Single.fromCallable {
            if (searchConfig.target == null) {
                searchConfig.target = DEFAULT_LOCATION
            }

            searchConfig.target ?: DEFAULT_LOCATION
        }
            .flatMapCompletable {
                cameraPersistence.update(Camera.ToPosition(it, Zoom.Away))
            }
            .andThen(
                locationProvider.singleUpdate()
                    .timeout(5, TimeUnit.SECONDS)
                    .toMaybe()
                    .onErrorComplete()
            )
            .flatMapCompletable { cameraPersistence.update(Camera.ToPosition(it, Zoom.Away)) }
            .applySchedulers(schedulersProvider)
}
