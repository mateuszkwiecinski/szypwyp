package pl.ccki.szypwyp.domain.models

sealed class LoadEvent {
    object Initial : LoadEvent()
    data class Loading(val id: PluginId) : LoadEvent()
    sealed class Finished : LoadEvent() {
        abstract val id: PluginId

        data class WithSuccess(override val id: PluginId) : Finished()
        data class WithError(override val id: PluginId, val throwable: Throwable) : Finished()
    }

    object Completed : LoadEvent()
}
