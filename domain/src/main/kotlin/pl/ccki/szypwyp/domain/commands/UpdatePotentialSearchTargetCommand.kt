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
    private val persistence: PotentialSearchTargetPersistence
) : Command<LatLng> {

    companion object {
        private val DEFAULT_DISTANCE = Kilometers(40)
    }

    override fun execute(param: LatLng) =
        persistence.last()
            .flatMapCompletable {
                if (it.distanceTo(param) > DEFAULT_DISTANCE || !persistence.locked) {
                    persistence.locked = false
                    persistence.update(param)
                } else {
                    Completable.complete()
                }
            }
}
