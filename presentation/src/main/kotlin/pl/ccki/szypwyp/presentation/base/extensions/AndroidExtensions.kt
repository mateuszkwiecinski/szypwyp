package pl.ccki.szypwyp.presentation.base.extensions

import pl.ccki.szypwyp.domain.models.Permission

internal val Permission.permissionName: String
    get() = when (this) {
        Permission.Location -> android.Manifest.permission.ACCESS_FINE_LOCATION
    }
