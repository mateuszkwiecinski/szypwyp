package pl.ccki.szypwyp.domain.repositories

import pl.ccki.szypwyp.domain.models.LatLng

interface SearchConfigRepository {
    var target: LatLng?
}
