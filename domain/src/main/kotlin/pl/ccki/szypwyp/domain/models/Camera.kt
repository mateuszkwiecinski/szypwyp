package pl.ccki.szypwyp.domain.models

sealed class Camera {
    data class ToPosition(val position: LatLng, val zoom: Zoom? = null) : Camera()
    data class ToGroup(val items: Iterable<LatLng>) : Camera()
    object ZoomIn : Camera()
    object ZoomOut : Camera()
}

enum class Zoom {
    Close, Away
}
