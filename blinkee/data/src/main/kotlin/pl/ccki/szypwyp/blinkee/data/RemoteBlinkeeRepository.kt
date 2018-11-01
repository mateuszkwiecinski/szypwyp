package pl.ccki.szypwyp.blinkee.data

import pl.ccki.szypwyp.blinkee.data.config.BlinkeeEndpoints
import pl.ccki.szypwyp.blinkee.domain.BlinkeeRepository
import pl.ccki.szypwyp.blinkee.data.models.BlinkeItemResponse
import pl.ccki.szypwyp.blinkee.data.models.Regions
import pl.ccki.szypwyp.blinkee.domain.models.BlinkeeMarkerModel
import pl.ccki.szypwyp.domain.models.LatLng
import pl.ccki.szypwyp.domain.models.MarkerModel
import javax.inject.Inject

class RemoteBlinkeeRepository @Inject constructor(
    private val endpoints: BlinkeeEndpoints
) : BlinkeeRepository {

    override fun getAll(): List<MarkerModel> {
        val response = endpoints.get(Regions.Wroclaw.regionId).execute()

        return response.body().let {
            it?.data?.items?.mapNotNull(this::map).orEmpty()
        }
    }

    private fun map(data: BlinkeItemResponse): MarkerModel? {
        val id = data.id?.toString() ?: return null
        val lat = data.position?.latitude ?: return null
        val lng = data.position.longitude ?: return null

        return BlinkeeMarkerModel(
            id = id,
            location = LatLng(lat, lng)
        )
    }
}
