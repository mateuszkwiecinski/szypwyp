package pl.ccki.szypwyp.domain

import io.reactivex.Completable
import io.reactivex.Single
import pl.ccki.szypwyp.domain.base.Query
import javax.inject.Inject

class RefreshVehiclesQuery @Inject constructor(
    private val configuration: ServicesConfigurationStorage,
    private val repository: VehiclesRepository,
    private val persistence: VehiclesPersistence,
    private val locationProvider: SearchConfigProvider
) : Query<Unit> {
    override fun run(param: Unit): Completable =
        allCall()
            .flatMap {
                Single.mergeDelayError(it).toList().map { it.flatten() }
            }
            .flatMapCompletable {
                persistence.update(it)
            }

    private fun allCall() =
        locationProvider.target?.let { target ->
            configuration.get().toSingle(defaultConfig).map {
                it.selectedItems.map {
                    Single.fromCallable {
                        repository.get(SearchModel(it, target))
                    }
                    // TODO: @mk 04/08/2018 think of errors here
                }
            }
        } ?: Single.just(emptyList())

    private val defaultConfig: ServiceConfigurationModel
        get() = ServiceConfigurationModel(ServiceType.values().toList())
}
