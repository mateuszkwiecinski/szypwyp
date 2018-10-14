package pl.ccki.szypwyp.goscooter

import pl.ccki.szypwyp.domain.models.LatLng
import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.goscooter.config.GoScooterEndpoints
import pl.ccki.szypwyp.goscooter.models.GoScooterResponse

class GoScooterRepository(
    private val endpoint: GoScooterEndpoints
) {

    fun getAll(): List<MarkerModel> {
        val response = endpoint.allAvailableDevices().execute()

        return response.body().orEmpty().mapNotNull {
            map(it)
        }
    }

    private fun map(data: GoScooterResponse): MarkerModel? {
        val id = data.id?.toString() ?: return null
        val lat = data.latitude ?: return null
        val lng = data.longitude ?: return null

        return MarkerModel(
            id = id,
            location = LatLng(lat, lng)
        )
    }
}
