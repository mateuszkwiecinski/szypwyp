package pl.ccki.szypwyp.presentation.base

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import pl.ccki.szypwyp.domain.base.SchedulersProvider
import javax.inject.Inject

class AndroidSchedulersProvider @Inject constructor() : SchedulersProvider {
    override val worker: Scheduler = Schedulers.io()
    override val postExecution: Scheduler = AndroidSchedulers.mainThread()
}
