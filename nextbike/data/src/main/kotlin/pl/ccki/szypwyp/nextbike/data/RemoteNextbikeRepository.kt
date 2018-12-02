package pl.ccki.szypwyp.nextbike.data

import pl.ccki.szypwyp.domain.models.LatLng
import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.nextbike.data.config.NextbikeEndpoints
import pl.ccki.szypwyp.nextbike.data.models.NextbikeResponse
import pl.ccki.szypwyp.nextbike.data.models.PlaceResponse
import pl.ccki.szypwyp.nextbike.data.models.regionId
import pl.ccki.szypwyp.nextbike.domain.NextbikeRepository
import pl.ccki.szypwyp.nextbike.domain.models.NextbikeMarkerModel
import pl.ccki.szypwyp.nextbike.domain.models.NextbikeRegion
import javax.inject.Inject

class RemoteNextbikeRepository @Inject constructor(
    private val endpoints: NextbikeEndpoints
) : NextbikeRepository {

    override fun getAll(region: Iterable<NextbikeRegion>): List<MarkerModel> =
        region.map {
            val response = endpoints.json(it.regionId).execute()

            response.body()?.let(this::map).orEmpty()
        }.flatten()

    private fun map(response: NextbikeResponse): List<MarkerModel> {
        val cities = response.countries.orEmpty().flatMap { it.cities.orEmpty() }
        val places = cities.flatMap { it.places.orEmpty() }

        return places.mapNotNull(this::map)
    }

    private fun map(response: PlaceResponse): NextbikeMarkerModel? {
        val lat = response.latitude ?: return null
        val lng = response.longitude ?: return null
        val id = response.id ?: return null
        val bikes = Math.max(0, (response.racksTotal ?: 0) - (response.racksFree ?: 0))
        return NextbikeMarkerModel(
            id = id.toString(),
            location = LatLng(lat, lng),
            address = response.name.orEmpty(),
            bikes = bikes,
            racks = response.racksTotal ?: 0,
            number = response.number
        )
    }
}
