package pl.ccki.szypwyp.presentation.map.models

sealed class MapEvent {
    object MyLocationPermissionError : MapEvent()
}
