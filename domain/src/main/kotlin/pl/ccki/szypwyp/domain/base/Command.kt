package pl.ccki.szypwyp.domain.base

import io.reactivex.Observable

interface Command<T : Any> {
    fun execute(): Observable<T>
}
