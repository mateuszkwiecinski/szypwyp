package pl.ccki.szypwyp.domain.base

import io.reactivex.Completable

interface Command<T> {
    fun execute(param: T): Completable
}
