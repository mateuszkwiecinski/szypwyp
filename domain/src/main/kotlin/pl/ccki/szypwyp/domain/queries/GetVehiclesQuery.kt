package pl.ccki.szypwyp.domain.queries

import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import pl.ccki.szypwyp.domain.base.Query
import pl.ccki.szypwyp.domain.base.SchedulersProvider
import pl.ccki.szypwyp.domain.base.applySchedulers
import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.domain.models.PluginId
import pl.ccki.szypwyp.domain.persistences.FiltersPersistence
import pl.ccki.szypwyp.domain.persistences.VehiclesData
import pl.ccki.szypwyp.domain.persistences.VehiclesPersistence
import javax.inject.Inject

class GetVehiclesQuery @Inject constructor(
    private val persistence: VehiclesPersistence,
    private val filtersPersistence: FiltersPersistence,
    private val schedulersProvider: SchedulersProvider
) : Query<Map<PluginId, List<MarkerModel>>> {

    override fun execute(): Observable<Map<PluginId, List<MarkerModel>>> =
        Observable.combineLatest(
            persistence.get(),
            filtersPersistence.observeDisabled(),
            BiFunction<VehiclesData, Set<PluginId>, VehiclesData> { vehicles, filters ->
                vehicles.filterKeys { !filters.contains(it) }.takeIf { it.isNotEmpty() } ?: vehicles
            }
        )
            .applySchedulers(schedulersProvider)
}
