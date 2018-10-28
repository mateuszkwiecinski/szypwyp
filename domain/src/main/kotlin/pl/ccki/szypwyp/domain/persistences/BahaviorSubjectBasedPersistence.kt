package pl.ccki.szypwyp.domain.persistences

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

interface DefaultPersistence<T> {
    fun get(): Observable<T>
    fun update(new: T): Completable
}

abstract class BehaviorSubjectBasedPersistence<T>(
    emptyValue: T? = null
) : DefaultPersistence<T> {
    private val subject = emptyValue?.let {
        BehaviorSubject.createDefault(it)
    } ?: BehaviorSubject.create<T>()

    override fun get(): Observable<T> = subject.hide()

    override fun update(new: T): Completable =
        Completable.fromAction {
            subject.onNext(new)
        }
}
