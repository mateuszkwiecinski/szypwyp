package pl.ccki.szypwyp.domain.persistences

import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import pl.ccki.szypwyp.domain.models.MapError
import pl.ccki.szypwyp.domain.models.MapEvent
import pl.ccki.szypwyp.domain.models.Progress
import javax.inject.Inject

interface MapEventsPersistence {

    fun progress(): Observable<Progress>

    fun errors(): Observable<MapError>

    fun update(progress: MapEvent)
}

class InMemoryBehaviorSubjectBasedMapEventsPersistence @Inject constructor() : MapEventsPersistence {

    private val errorsSubject = BehaviorSubject.create<MapError>()
    private val progressSubject = BehaviorSubject.create<Progress>()

    override fun progress() = progressSubject.hide()

    override fun errors() = errorsSubject.hide()

    override fun update(progress: MapEvent) =
        when (progress) {
            is Progress -> progressSubject.onNext(progress)
            is MapError -> errorsSubject.onNext(progress)
        }
}
