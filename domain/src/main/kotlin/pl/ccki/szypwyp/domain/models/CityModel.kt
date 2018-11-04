package pl.ccki.szypwyp.domain.models

data class CityModel(val id: CityId, val center: LatLng, val radius: Kilometers)

interface CityId
