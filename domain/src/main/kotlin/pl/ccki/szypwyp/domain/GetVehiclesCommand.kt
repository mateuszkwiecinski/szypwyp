package pl.ccki.szypwyp.domain

import io.reactivex.Observable
import pl.ccki.szypwyp.domain.base.Command
import javax.inject.Inject

class GetVehiclesCommand @Inject constructor(
    private val persistence: VehiclesPersistence
) : Command<List<MarkerModel>> {

    override fun run(): Observable<List<MarkerModel>> =
        persistence.get()
}
