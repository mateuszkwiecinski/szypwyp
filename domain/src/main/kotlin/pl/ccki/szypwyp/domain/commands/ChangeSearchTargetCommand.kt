package pl.ccki.szypwyp.domain.commands

import io.reactivex.Completable
import pl.ccki.szypwyp.domain.base.Command
import pl.ccki.szypwyp.domain.base.execute
import pl.ccki.szypwyp.domain.persistences.PotentialSearchTargetPersistence
import pl.ccki.szypwyp.domain.persistences.VehiclesPersistence
import pl.ccki.szypwyp.domain.repositories.SearchConfigRepository
import javax.inject.Inject

class ChangeSearchTargetCommand @Inject constructor(
    private val persistence: PotentialSearchTargetPersistence,
    private val refreshVehiclesCommand: RefreshVehiclesCommand,
    private val repository: SearchConfigRepository,
    private val vehiclesPersistence: VehiclesPersistence
) : Command<Unit> {

    override fun execute(param: Unit) =
        persistence.last()
            .flatMapCompletable {
                Completable.fromAction {
                    repository.target = it
                }.andThen(persistence.update(it))
            }
            .andThen(vehiclesPersistence.clear())
            .andThen(refreshVehiclesCommand.execute())
}
