package pl.ccki.szypwyp.domain.models

sealed class MapEvent

sealed class Progress : MapEvent() {
    object Initial : Progress()
    data class Loading(val id: PluginId) : Progress()
    data class Finished(val id: PluginId) : Progress()
    object Completed : Progress()
}

sealed class MapError : MapEvent() {
    data class SpecificPluginError(val id: PluginId, val issue: Throwable) : MapError()
    data class Unknown(val issue: Throwable) : MapError()
}
