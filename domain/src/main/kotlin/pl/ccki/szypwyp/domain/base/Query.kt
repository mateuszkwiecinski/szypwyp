package pl.ccki.szypwyp.domain.base

import io.reactivex.Completable

interface Query<T> {
    fun run(param: T): Completable
}
