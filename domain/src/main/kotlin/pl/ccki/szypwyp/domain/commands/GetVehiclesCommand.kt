package pl.ccki.szypwyp.domain.commands

import io.reactivex.Observable
import pl.ccki.szypwyp.domain.base.Command
import pl.ccki.szypwyp.domain.base.SchedulersProvider
import pl.ccki.szypwyp.domain.base.applySchedulers
import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.domain.models.ServiceId
import pl.ccki.szypwyp.domain.persistences.VehiclesPersistence
import javax.inject.Inject

class GetVehiclesCommand @Inject constructor(
    private val persistence: VehiclesPersistence,
    private val schedulersProvider: SchedulersProvider
) : Command<Map<ServiceId, List<MarkerModel>>> {

    override fun execute(): Observable<Map<ServiceId, List<MarkerModel>>> =
        persistence.get().applySchedulers(schedulersProvider)
}
