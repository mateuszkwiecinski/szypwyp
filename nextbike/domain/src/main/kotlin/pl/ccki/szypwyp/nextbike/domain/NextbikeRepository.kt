package pl.ccki.szypwyp.nextbike.domain

import pl.ccki.szypwyp.nextbike.domain.models.NextbikeRegion
import pl.ccki.szypwyp.domain.models.MarkerModel

interface NextbikeRepository {
    fun getAll(region: Iterable<NextbikeRegion>): List<MarkerModel>
}
