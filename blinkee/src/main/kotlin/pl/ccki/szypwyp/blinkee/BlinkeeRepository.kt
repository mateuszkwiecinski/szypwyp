package pl.ccki.szypwyp.blinkee

import pl.ccki.szypwyp.domain.LatLng
import pl.ccki.szypwyp.domain.MarkerModel
import pl.ccki.szypwyp.domain.ServiceType

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

    private fun map(it: BlinkeItemResponse): MarkerModel? {
        val id = it.id?.toString() ?: return null
        val lat = it.position?.latitude ?: return null
        val lng = it.position?.latitude ?: return null
        return MarkerModel(
            id = id,
            name = null,
            location = LatLng(lat, lng),
            type = ServiceType.Blinkee
        )
    }
}