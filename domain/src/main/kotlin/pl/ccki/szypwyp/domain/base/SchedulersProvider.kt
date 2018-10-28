package pl.ccki.szypwyp.domain.base

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Scheduler

interface SchedulersProvider {
    val worker: Scheduler
    val postExecution: Scheduler
}

fun <T : Any> Observable<T>.applySchedulers(schedulersProvider: SchedulersProvider) =
    subscribeOn(schedulersProvider.worker).observeOn(schedulersProvider.postExecution)

fun Completable.applySchedulers(schedulersProvider: SchedulersProvider) =
    subscribeOn(schedulersProvider.worker).observeOn(schedulersProvider.postExecution)
