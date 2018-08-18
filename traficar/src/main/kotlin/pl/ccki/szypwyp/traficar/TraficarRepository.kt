package pl.ccki.szypwyp.traficar

import pl.ccki.szypwyp.domain.models.LatLng
import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.traficar.config.TraficarEndpoints
import pl.ccki.szypwyp.traficar.models.Car
import pl.ccki.szypwyp.traficar.models.Regions

class TraficarRepository(
    private val endpoints: TraficarEndpoints
) {
    fun getAll(): List<MarkerModel> {
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
        val lng = data.latitude ?: return null
        return MarkerModel(
            id = id,
            location = LatLng(lat, lng)
        )
    }
}
