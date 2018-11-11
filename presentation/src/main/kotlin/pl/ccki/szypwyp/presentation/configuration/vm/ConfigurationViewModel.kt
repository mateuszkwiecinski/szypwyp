package pl.ccki.szypwyp.presentation.configuration.vm

import com.instabug.bug.BugReporting
import pl.ccki.szypwyp.presentation.base.BaseViewModel
import javax.inject.Inject

class ConfigurationViewModel @Inject constructor(
) : BaseViewModel() {

    fun onClickReportABug() {
        BugReporting.invoke()
    }

    fun onClickLicenses() {

    }

    fun onClickAboutApp() {

    }
}
