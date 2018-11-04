package pl.ccki.szypwyp.domain.commands

import io.reactivex.Completable
import pl.ccki.szypwyp.domain.base.Command
import pl.ccki.szypwyp.domain.models.Kilometers
import pl.ccki.szypwyp.domain.models.LatLng
import pl.ccki.szypwyp.domain.models.compareTo
import pl.ccki.szypwyp.domain.models.distanceTo
import pl.ccki.szypwyp.domain.persistences.PotentialSearchTargetPersistence
import javax.inject.Inject

class UpdatePotentialSearchTargetCommand @Inject constructor(
    private val targetPersistence: PotentialSearchTargetPersistence
) : Command<LatLng> {

    companion object {
        private val DEFAULT_DISTANCE = Kilometers(70)
    }

    override fun execute(param: LatLng) =
        targetPersistence.last()
            .flatMapCompletable {
                if (it.distanceTo(param) > DEFAULT_DISTANCE) {
                    targetPersistence.update(param)
                } else {
                    Completable.complete()
                }
            }
}
