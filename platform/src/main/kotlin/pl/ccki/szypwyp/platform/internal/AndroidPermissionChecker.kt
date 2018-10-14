package pl.ccki.szypwyp.platform.internal

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import pl.ccki.szypwyp.domain.models.Permission
import javax.inject.Inject

class AndroidPermissionChecker @Inject constructor(
    private val context: Context
) {
    fun checkPermission(toCheck: Permission) =
        ContextCompat.checkSelfPermission(context, toCheck.permissionName) == PackageManager.PERMISSION_GRANTED
}

internal val Permission.permissionName: String
    get() = when (this) {
        Permission.Location -> android.Manifest.permission.ACCESS_COARSE_LOCATION
    }
