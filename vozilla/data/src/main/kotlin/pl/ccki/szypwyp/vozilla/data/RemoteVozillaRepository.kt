package pl.ccki.szypwyp.vozilla.data

import pl.ccki.szypwyp.domain.models.LatLng
import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.vozilla.data.config.VozillaEndpoints
import pl.ccki.szypwyp.vozilla.domain.VozillaRepository
import pl.ccki.szypwyp.vozilla.data.model.ObjectResponse
import pl.ccki.szypwyp.domain.models.Kilometers
import pl.ccki.szypwyp.domain.models.Percent
import pl.ccki.szypwyp.vozilla.domain.models.VozillaMarkerModel
import javax.inject.Inject

class RemoteVozillaRepository @Inject constructor(
    private val endpoints: VozillaEndpoints
) : VozillaRepository {

    override fun getAll(): List<MarkerModel> =
        endpoints.get().execute().let {
            it.body()?.list?.mapNotNull(this::map).orEmpty()
        }

    private fun map(param: ObjectResponse): MarkerModel? {
        val id = param.id ?: return null
        val lat = param.location?.latitude ?: return null
        val lng = param.location.longitude ?: return null

        return VozillaMarkerModel(id = id,
            location = LatLng(lat, lng),
            name = param.name.orEmpty(),
            range = param.rangeKm?.let(::Kilometers),
            battery = param.batteryLevelPct?.let(::Percent)
        )
    }
}
