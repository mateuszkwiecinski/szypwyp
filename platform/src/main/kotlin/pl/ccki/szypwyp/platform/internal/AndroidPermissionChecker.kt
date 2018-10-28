package pl.ccki.szypwyp.platform.internal

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import pl.ccki.szypwyp.domain.models.Permission
import pl.ccki.szypwyp.domain.repositories.PermissionChecker
import javax.inject.Inject

class AndroidPermissionChecker @Inject constructor(
    private val context: Context
) : PermissionChecker {

    override fun check(permission: Permission) =
        ContextCompat.checkSelfPermission(context, permission.permissionName) == PackageManager.PERMISSION_GRANTED
}

private val Permission.permissionName: String
    get() = when (this) {
        Permission.Location -> android.Manifest.permission.ACCESS_FINE_LOCATION
    }
