package pl.ccki.szypwyp.blinkee.data

import pl.ccki.szypwyp.blinkee.data.config.BlinkeeEndpoints
import pl.ccki.szypwyp.blinkee.domain.BlinkeeRepository
import pl.ccki.szypwyp.blinkee.data.models.BlinkeItemResponse
import pl.ccki.szypwyp.blinkee.data.models.BlinkeeResponse
import pl.ccki.szypwyp.blinkee.data.models.regionId
import pl.ccki.szypwyp.blinkee.domain.models.BlinkeeRegion
import pl.ccki.szypwyp.blinkee.domain.models.BlinkeeMarkerModel
import pl.ccki.szypwyp.blinkee.domain.models.BlinkeeType
import pl.ccki.szypwyp.domain.models.LatLng
import pl.ccki.szypwyp.domain.models.MarkerModel
import timber.log.Timber
import timber.log.error
import javax.inject.Inject

class RemoteBlinkeeRepository @Inject constructor(
    private val endpoints: BlinkeeEndpoints
) : BlinkeeRepository {

    override fun getAll(region: Iterable<BlinkeeRegion>): List<MarkerModel> =
        region.map {
            val response = endpoints.get(it.regionId).execute()

            response.body().let(this::map)
        }.flatten()

    private fun map(response: BlinkeeResponse?): List<MarkerModel> =
        response?.data?.items?.mapNotNull(this::map).orEmpty()

    private fun map(data: BlinkeItemResponse): MarkerModel? {
        val id = data.id?.toString() ?: return null
        val lat = data.position?.latitude ?: return null
        val lng = data.position.longitude ?: return null
        val type = when (data.type) {
            "scooter" -> BlinkeeType.Scooter
            "bike" -> BlinkeeType.Bike
            else -> {
                Timber.error { data.toString() }
                return null
            }
        }

        return BlinkeeMarkerModel(
            id = id,
            location = LatLng(lat, lng),
            name = "Blinkee ${data.type}",
            type = type
        )
    }
}
