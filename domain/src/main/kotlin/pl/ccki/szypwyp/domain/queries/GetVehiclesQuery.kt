package pl.ccki.szypwyp.domain.queries

import io.reactivex.Observable
import pl.ccki.szypwyp.domain.base.Query
import pl.ccki.szypwyp.domain.base.SchedulersProvider
import pl.ccki.szypwyp.domain.base.applySchedulers
import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.domain.models.PluginId
import pl.ccki.szypwyp.domain.persistences.VehiclesPersistence
import javax.inject.Inject

class GetVehiclesQuery @Inject constructor(
    private val persistence: VehiclesPersistence,
    private val schedulersProvider: SchedulersProvider
) : Query<Map<PluginId, List<MarkerModel>>> {

    override fun execute(): Observable<Map<PluginId, List<MarkerModel>>> =
        persistence.get()
            .applySchedulers(schedulersProvider)
}
