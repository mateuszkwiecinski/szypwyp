package pl.ccki.szypwyp.presentation.dialogs

import android.content.Context
import androidx.appcompat.app.AlertDialog
import pl.ccki.szypwyp.presentation.R

class AppInfoDialog(
    context: Context
) {

    private val builder = AlertDialog.Builder(context)

    private val appVersion = context.packageManager.getPackageInfo(context.packageName, 0).versionName

    init {
        builder.apply {
            setTitle(R.string.dialog_app_info_title)
            setMessage(context.getString(R.string.dialog_app_info_message, appVersion))
            setPositiveButton(android.R.string.ok) { _, _ -> }
        }
    }

    fun show() {
        builder.show()
    }
}
