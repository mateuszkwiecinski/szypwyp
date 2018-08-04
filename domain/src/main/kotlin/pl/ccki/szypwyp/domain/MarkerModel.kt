package pl.ccki.szypwyp.domain

data class MarkerModel(
    val id: String,
    val name: String?,
    val location: LatLng,
    val type: ServiceType
)
