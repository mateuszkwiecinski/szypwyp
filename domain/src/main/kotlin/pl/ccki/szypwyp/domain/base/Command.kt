package pl.ccki.szypwyp.domain.base

import io.reactivex.Observable

interface Command<T : Any> {
    fun run(): Observable<T>
}