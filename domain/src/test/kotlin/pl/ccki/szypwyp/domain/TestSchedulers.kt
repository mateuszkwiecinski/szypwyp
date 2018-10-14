package pl.ccki.szypwyp.domain

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import pl.ccki.szypwyp.domain.base.SchedulersProvider

object TestSchedulers : SchedulersProvider {
    override val worker: Scheduler = Schedulers.trampoline()
    override val postExecution: Scheduler = Schedulers.trampoline()
}
