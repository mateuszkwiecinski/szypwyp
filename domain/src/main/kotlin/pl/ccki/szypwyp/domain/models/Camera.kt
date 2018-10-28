package pl.ccki.szypwyp.domain.models

sealed class Camera {
    data class ToPosition(
        val position: LatLng,
        val minZoom: Zoom? = null,
        val maxZoom: Zoom? = null
    ) : Camera()

    data class ToGroup(val items: Iterable<LatLng>) : Camera()
    object ZoomIn : Camera()
    object ZoomOut : Camera()
}

enum class Zoom {
    Close, Away
}
