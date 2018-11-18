package pl.ccki.szypwyp.domain.commands

import io.reactivex.Completable
import pl.ccki.szypwyp.domain.base.Command
import pl.ccki.szypwyp.domain.base.execute
import pl.ccki.szypwyp.domain.persistences.CurrentSearchTargetPersistence
import pl.ccki.szypwyp.domain.persistences.PotentialSearchTargetPersistence
import pl.ccki.szypwyp.domain.persistences.VehiclesPersistence
import pl.ccki.szypwyp.domain.repositories.SearchConfigRepository
import javax.inject.Inject

class ChangeSearchTargetCommand @Inject constructor(
    private val potentialTarget: PotentialSearchTargetPersistence,
    private val currentTarget: CurrentSearchTargetPersistence,
    private val refreshVehiclesCommand: RefreshVehiclesCommand,
    private val repository: SearchConfigRepository,
    private val vehiclesPersistence: VehiclesPersistence
) : Command<Unit> {

    override fun execute(param: Unit) =
        potentialTarget.last()
            .flatMapCompletable {
                Completable.fromAction {
                    repository.target = it
                    potentialTarget.locked = true
                }
                    .andThen(currentTarget.update(it))
                    .andThen(potentialTarget.update(it))
            }
            .andThen(vehiclesPersistence.clear())
            .andThen(refreshVehiclesCommand.execute())
}
