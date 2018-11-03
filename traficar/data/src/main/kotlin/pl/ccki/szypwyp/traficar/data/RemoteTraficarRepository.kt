package pl.ccki.szypwyp.traficar.data

import pl.ccki.szypwyp.domain.models.Kilometers
import pl.ccki.szypwyp.domain.models.LatLng
import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.domain.models.Percent
import pl.ccki.szypwyp.traficar.data.config.TraficarEndpoints
import pl.ccki.szypwyp.traficar.domain.TraficarRepository
import pl.ccki.szypwyp.traficar.domain.models.TraficarMarkerModel
import pl.ccki.szypwyp.traficar.data.models.Car
import pl.ccki.szypwyp.traficar.data.models.Regions
import javax.inject.Inject
import kotlin.math.min

class RemoteTraficarRepository @Inject constructor(
    private val endpoints: TraficarEndpoints
) : TraficarRepository {
    companion object {
        private const val MAX_RANGE = 600.0
        private const val CALCULATED_MULTIPLIER_PROBABLY_AVERAGE_FUEL_CONSUMPTION = 12.5
    }

    override fun getAll(): List<MarkerModel> {
        val response = endpoints.get(Regions.Wroclaw.regionId).execute()
        return response.body()?.let {
            it.cars?.mapNotNull(this::map).orEmpty()
        } ?: emptyList()
    }

    private fun map(data: Car): MarkerModel? {
        val id = data.id?.toString() ?: return null
        val lat = data.latitude ?: return null
        val lng = data.longitude ?: return null
        val fuel = data.fuel?.let {
            val percentage = it * 100 * CALCULATED_MULTIPLIER_PROBABLY_AVERAGE_FUEL_CONSUMPTION / MAX_RANGE
            min(percentage.toInt() , 100)
        }?.let(::Percent)

        return TraficarMarkerModel(
            id = id,
            location = LatLng(lat, lng),
            fuel = fuel,
            range = data.fuel?.let { it * CALCULATED_MULTIPLIER_PROBABLY_AVERAGE_FUEL_CONSUMPTION }?.toInt()?.let(::Kilometers),
            name = data.model.orEmpty()
        )
    }
}
