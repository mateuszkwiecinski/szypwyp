package pl.ccki.szypwyp.domain.persistences

import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import pl.ccki.szypwyp.domain.models.LoadEvent
import pl.ccki.szypwyp.domain.models.PluginId
import pl.ccki.szypwyp.domain.models.StateModel
import javax.inject.Inject

interface MapEventsPersistence {

    fun events(): Observable<Map<PluginId, StateModel>>

    fun update(event: LoadEvent)
}

class InMemoryBehaviorSubjectBasedMapEventsPersistence @Inject constructor() : MapEventsPersistence {

    private val eventsSubject: BehaviorSubject<Map<PluginId, StateModel>> =
        BehaviorSubject.createDefault(emptyMap())

    override fun events() =
        eventsSubject.hide()

    override fun update(event: LoadEvent) {
        val current = eventsSubject.value.orEmpty().toMutableMap()
        when (event) {
            LoadEvent.Initial -> {
                current.clear()
            }
            is LoadEvent.Loading -> {
                current[event.id] = StateModel.Loading
            }
            is LoadEvent.Finished.WithSuccess -> {
                current[event.id] = StateModel.Succeeded
            }
            is LoadEvent.Finished.WithError -> {
                current[event.id] = StateModel.Failed(event.throwable)
            }
            LoadEvent.Completed -> Unit
        }

        eventsSubject.onNext(current)
    }
}
