package pl.ccki.szypwyp.presentation.dialogs

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import pl.ccki.szypwyp.domain.models.Permission
import pl.ccki.szypwyp.presentation.R

class PermissionBlockedDialog(
    permission: Permission,
    private val context: Context,
    private val action: (Intent) -> Unit
) {
    private val builder = AlertDialog.Builder(context)

    init {
        val message = when (permission) {
            Permission.Location -> R.string.dialog_permission_blocked_location_message
        }
        builder.apply{
            setTitle(R.string.dialog_permission_blocked_title)
            setMessage(message)
            setPositiveButton(R.string.dialog_permission_blocked_positive) { _, _ ->
                action(getGoToSettingsIntent())
            }
            setNegativeButton(android.R.string.cancel) { _, _ -> }
        }
    }

    private fun getGoToSettingsIntent(): Intent {
        val uri = Uri.fromParts("package", context.packageName, null)

        return Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, uri).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
            addCategory(Intent.CATEGORY_DEFAULT)
        }
    }

    fun show() {
        builder.show()
    }
}
