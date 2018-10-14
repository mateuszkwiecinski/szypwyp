package pl.ccki.szypwyp.domain.base

import io.reactivex.Observable

interface Query<T : Any> {
    fun execute(): Observable<T>
}
