package pl.ccki.szypwyp.goscooter.data

import pl.ccki.szypwyp.domain.models.LatLng
import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.goscooter.data.config.GoScooterEndpoints
import pl.ccki.szypwyp.goscooter.data.models.GoScooterResponse
import pl.ccki.szypwyp.goscooter.domain.GoScooterRepository
import javax.inject.Inject

class RemoteGoScooterRepository @Inject constructor(
    private val endpoint: GoScooterEndpoints
) : GoScooterRepository {

    override fun getAll(): List<MarkerModel> {
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
