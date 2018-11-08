package pl.ccki.szypwyp.domain.models

sealed class StateModel {
    object Loading : StateModel()
    object Succeeded : StateModel()
    data class Failed(val throwable: Throwable) : StateModel()
}
