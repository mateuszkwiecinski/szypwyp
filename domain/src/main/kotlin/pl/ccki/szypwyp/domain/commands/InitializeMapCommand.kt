package pl.ccki.szypwyp.domain.commands

import io.reactivex.Completable
import io.reactivex.Single
import pl.ccki.szypwyp.domain.base.Command
import pl.ccki.szypwyp.domain.base.SchedulersProvider
import pl.ccki.szypwyp.domain.base.applySchedulers
import pl.ccki.szypwyp.domain.base.execute
import pl.ccki.szypwyp.domain.models.Camera
import pl.ccki.szypwyp.domain.models.DEFAULT_LOCATION
import pl.ccki.szypwyp.domain.models.Zoom
import pl.ccki.szypwyp.domain.persistences.CameraPersistence
import pl.ccki.szypwyp.domain.persistences.PotentialSearchTargetPersistence
import pl.ccki.szypwyp.domain.providers.LocationProvider
import pl.ccki.szypwyp.domain.repositories.SearchConfigRepository
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class InitializeMapCommand @Inject constructor(
    private val cameraPersistence: CameraPersistence,
    private val locationProvider: LocationProvider,
    private val configRepository: SearchConfigRepository,
    private val targetPersistence: PotentialSearchTargetPersistence,
    private val schedulersProvider: SchedulersProvider,
    private val refreshVehiclesCommand: RefreshVehiclesCommand
) : Command<Unit> {

    override fun execute(param: Unit): Completable =
        Single.fromCallable {
            if (configRepository.target == null) {
                configRepository.target = DEFAULT_LOCATION
            }

            configRepository.target ?: DEFAULT_LOCATION
        }
            .flatMapCompletable {
                cameraPersistence.update(Camera.ToPosition(it, maxZoom = Zoom.Away))
            }
            .andThen(
                locationProvider.singleUpdate(schedulersProvider.worker)
                    .timeout(5, TimeUnit.SECONDS)
                    .toMaybe()
                    .onErrorComplete()
            )
            .flatMapCompletable {
                configRepository.target = it
                cameraPersistence.update(Camera.ToPosition(it, maxZoom = Zoom.Away)
                )
            }
            .andThen(
                Single.fromCallable {
                    configRepository.target
                }.flatMapCompletable {
                    targetPersistence.update(it)
                }
            )
            .andThen(refreshVehiclesCommand.execute())
            .applySchedulers(schedulersProvider)
}
