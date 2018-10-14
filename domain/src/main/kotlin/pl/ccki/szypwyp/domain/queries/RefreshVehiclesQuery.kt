package pl.ccki.szypwyp.domain.queries

import io.reactivex.Completable
import io.reactivex.Single
import pl.ccki.szypwyp.domain.base.Query
import pl.ccki.szypwyp.domain.base.SchedulersProvider
import pl.ccki.szypwyp.domain.base.applySchedulers
import pl.ccki.szypwyp.domain.models.DEFAULT_LOCATION
import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.domain.models.ServiceId
import pl.ccki.szypwyp.domain.persistences.VehiclesPersistence
import pl.ccki.szypwyp.domain.repositories.SearchConfigRepository
import pl.ccki.szypwyp.domain.repositories.ServicesConfigurationRepository
import pl.ccki.szypwyp.domain.services.ExternalService
import javax.inject.Inject

class RefreshVehiclesQuery @Inject constructor(
    private val configuration: ServicesConfigurationRepository,
    private val registeredServices: Set<@JvmSuppressWildcards ExternalService>,
    private val persistence: VehiclesPersistence,
    private val searchConfig: SearchConfigRepository,
    private val schedulersProvider: SchedulersProvider
) : Query<Unit> {

    override fun execute(param: Unit): Completable =
        Single.fromCallable<List<Single<RequestDto>>> {
            val servicesToCall = findServices()
            val target = searchConfig.target ?: DEFAULT_LOCATION

            servicesToCall.map {
                Single.fromCallable {
                    RequestDto(it.id, it.findInLocation(target))
                }
            }
        }
            .flatMap {
                Single.mergeDelayError(it).toMap(RequestDto::id, RequestDto::result)
            }
            .flatMapCompletable {
                persistence.update(it)
            }
            .applySchedulers(schedulersProvider)

    private fun findServices(): Iterable<ExternalService> {
        val services = configuration.selected ?: return registeredServices

        return registeredServices.filter {
            services.contains(it.id)
        }
    }
}

private data class RequestDto(val id: ServiceId, val result: List<MarkerModel>)
