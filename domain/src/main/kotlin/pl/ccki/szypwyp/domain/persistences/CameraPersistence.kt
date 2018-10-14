package pl.ccki.szypwyp.domain.persistences

import io.reactivex.Completable
import io.reactivex.Observable
import pl.ccki.szypwyp.domain.models.Camera

interface CameraPersistence {
    fun get(): Observable<Camera>
    fun update(new: Camera): Completable
}
