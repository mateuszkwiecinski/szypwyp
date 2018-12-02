package pl.ccki.szypwyp.presentation.configuration.vm

import io.reactivex.subjects.PublishSubject
import pl.ccki.szypwyp.presentation.interfaces.base.BaseViewModel
import javax.inject.Inject

class ConfigurationViewModel @Inject constructor(
) : BaseViewModel() {

    val navigation = PublishSubject.create<NavigationEvent>()

    fun onClickReportABug() {
        navigation.onNext(NavigationEvent.ReportABug)
    }

    fun onClickLicenses() {
        navigation.onNext(NavigationEvent.Licenses)
    }

    fun onClickAboutApp() {
        navigation.onNext(NavigationEvent.About)
    }
}

enum class NavigationEvent {
    ReportABug,
    Licenses,
    About
}
