package pl.ccki.szypwyp.domain.errors

import pl.ccki.szypwyp.domain.models.Permission

sealed class SzypException : Exception()

sealed class Android : SzypException() {
    data class MissingPermission(val which: Permission) : Android()
}
