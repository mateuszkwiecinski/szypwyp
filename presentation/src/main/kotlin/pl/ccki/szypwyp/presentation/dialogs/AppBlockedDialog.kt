package pl.ccki.szypwyp.presentation.dialogs

import android.app.Activity
import androidx.appcompat.app.AlertDialog
import pl.ccki.szypwyp.presentation.R

class AppBlockedDialog(
    activity: Activity,
    action: () -> Unit
) {

    private val builder = AlertDialog.Builder(activity)

    init {
        builder.apply {
            setTitle(R.string.dialog_app_blocked_title)
            setMessage(R.string.dialog_app_blocked_message)
            setPositiveButton(R.string.dialog_app_blocked_positive) { _, _ ->
                action()
            }
            setNegativeButton(android.R.string.cancel) { _, _ -> activity.finishAffinity() }
        }
    }

    fun show() {
        builder.show()
    }
}
