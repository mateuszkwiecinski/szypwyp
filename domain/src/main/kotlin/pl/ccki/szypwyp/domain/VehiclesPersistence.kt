package pl.ccki.szypwyp.domain

import io.reactivex.Completable
import io.reactivex.Observable

interface VehiclesPersistence {
    fun get(): Observable<List<MarkerModel>>

    fun update(new: List<MarkerModel>): Completable
}