package pl.ccki.szypwyp.traficar.data

import pl.ccki.szypwyp.domain.models.LatLng
import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.traficar.data.config.TraficarEndpoints
import pl.ccki.szypwyp.traficar.domain.TraficarRepository
import pl.ccki.szypwyp.traficar.models.Car
import pl.ccki.szypwyp.traficar.models.Regions
import javax.inject.Inject

class RemoteTraficarRepository @Inject constructor(
    private val endpoints: TraficarEndpoints
) : TraficarRepository {
    override fun getAll(): List<MarkerModel> {
        val response = endpoints.get(Regions.Wroclaw.regionId).execute()
        return response.body()?.let {
            it.cars?.mapNotNull {
                map(it)
            }.orEmpty()
        } ?: emptyList()
    }

    private fun map(data: Car): MarkerModel? {
        val id = data.id?.toString() ?: return null
        val lat = data.latitude ?: return null
        val lng = data.longitude ?: return null
        return MarkerModel(
            id = id,
            location = LatLng(lat, lng)
        )
    }
}
