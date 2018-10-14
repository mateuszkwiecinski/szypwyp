package pl.ccki.szypwyp.domain.queries

import io.reactivex.Observable
import pl.ccki.szypwyp.domain.base.Query
import pl.ccki.szypwyp.domain.base.SchedulersProvider
import pl.ccki.szypwyp.domain.base.applySchedulers
import pl.ccki.szypwyp.domain.models.Camera
import pl.ccki.szypwyp.domain.persistences.CameraPersistence
import javax.inject.Inject

class GetCameraQuery @Inject constructor(
    private val cameraPersistence: CameraPersistence,
    private val schedulersProvider: SchedulersProvider
) : Query<Camera> {

    override fun execute(): Observable<Camera> {
        return cameraPersistence.get().applySchedulers(schedulersProvider)
    }
}
