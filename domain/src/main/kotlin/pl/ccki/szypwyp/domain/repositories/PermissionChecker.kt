package pl.ccki.szypwyp.domain.repositories

import pl.ccki.szypwyp.domain.models.Permission

interface PermissionChecker {
    fun check(permission: Permission): Boolean
}
