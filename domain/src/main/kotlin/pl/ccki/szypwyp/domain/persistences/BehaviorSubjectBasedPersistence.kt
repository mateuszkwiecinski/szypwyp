package pl.ccki.szypwyp.domain.persistences

import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

interface DefaultPersistence<T> {
    fun get(): Observable<T>
    fun current(): Maybe<T>
    fun update(new: T): Completable
}

abstract class BehaviorSubjectBasedPersistence<T>(
    emptyValue: (() -> T?)? = null
) : DefaultPersistence<T> {
    private val subject by lazy {
        emptyValue?.invoke()?.let {
            BehaviorSubject.createDefault(it)
        } ?: BehaviorSubject.create<T>()
    }

    override fun get(): Observable<T> = subject.hide()

    override fun current(): Maybe<T> =
        Maybe.fromCallable {
            subject.value
        }

    override fun update(new: T): Completable =
        Completable.fromAction {
            subject.onNext(new)
        }

    fun last(): Maybe<T> =
        Maybe.fromCallable<T> {
            subject.value
        }
}
