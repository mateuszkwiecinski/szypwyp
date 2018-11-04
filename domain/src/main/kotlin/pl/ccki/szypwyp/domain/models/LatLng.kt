package pl.ccki.szypwyp.domain.models

data class LatLng(val latitude: Double, val longitude: Double)

fun LatLng.distanceTo(other: LatLng): Kilometers {
    val earthRadius = 3958.75
    val latDiff = Math.toRadians(other.latitude - latitude)
    val lngDiff = Math.toRadians(other.longitude - longitude)
    val a = Math.sin(latDiff / 2) * Math.sin(latDiff / 2) +
        Math.cos(Math.toRadians(latitude)) * Math.cos(Math.toRadians(other.latitude)) * Math.sin(lngDiff / 2) * Math.sin(lngDiff / 2)
    val c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))
    val distance = earthRadius * c

    val meterConversion = 1609

    return Kilometers((distance * meterConversion / 1000).toInt())
}
