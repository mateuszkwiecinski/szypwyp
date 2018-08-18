package pl.ccki.szypwyp.domain.base

import io.reactivex.Completable

interface Query<T> {
    fun execute(param: T): Completable
}
