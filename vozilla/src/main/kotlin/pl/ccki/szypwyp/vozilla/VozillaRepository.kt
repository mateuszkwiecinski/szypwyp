package pl.ccki.szypwyp.vozilla

import pl.ccki.szypwyp.domain.LatLng
import pl.ccki.szypwyp.domain.MarkerModel
import pl.ccki.szypwyp.domain.ServiceType
import pl.ccki.szypwyp.vozilla.config.VozillaEndpoints
import pl.ccki.szypwyp.vozilla.model.ObjectResponse

class VozillaRepository(
    private val endpoints: VozillaEndpoints
) {

    fun getAll(): List<MarkerModel> =
        endpoints.get().execute().let {
            it.body()?.list?.mapNotNull { map(it) }.orEmpty()
        }

    private fun map(param: ObjectResponse): MarkerModel? {
        val id = param.id ?: return null
        val lat = param.location?.latitude ?: return null
        val lng = param.location?.longitude ?: return null

        return MarkerModel(id = id,
            location = LatLng(lat, lng),
            type = ServiceType.Vozilla
        )
    }
}
