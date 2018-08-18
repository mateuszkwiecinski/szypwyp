package pl.ccki.szypwyp.blinkee

import pl.ccki.szypwyp.blinkee.config.BlinkeeEndpoints
import pl.ccki.szypwyp.blinkee.models.BlinkeItemResponse
import pl.ccki.szypwyp.blinkee.models.Regions
import pl.ccki.szypwyp.domain.models.LatLng
import pl.ccki.szypwyp.domain.models.MarkerModel

class BlinkeeRepository(
    private val endpoints: BlinkeeEndpoints
) {
    fun getAll(): List<MarkerModel> {
        val response = endpoints.get(Regions.Wroclaw.regionId).execute()
        return response.body()?.let {
            it.data?.items?.mapNotNull {
                map(it)
            }.orEmpty()
        } ?: emptyList()
    }

    private fun map(data: BlinkeItemResponse): MarkerModel? {
        val id = data.id?.toString() ?: return null
        val lat = data.position?.latitude ?: return null
        val lng = data.position?.latitude ?: return null
        return MarkerModel(
            id = id,
            location = LatLng(lat, lng)
        )
    }
}
