package pl.ccki.szypwyp.domain.commands

import io.reactivex.Completable
import io.reactivex.Observable
import pl.ccki.szypwyp.domain.base.Command
import pl.ccki.szypwyp.domain.base.SchedulersProvider
import pl.ccki.szypwyp.domain.base.applySchedulers
import pl.ccki.szypwyp.domain.models.Camera
import pl.ccki.szypwyp.domain.persistences.CameraPersistence
import pl.ccki.szypwyp.domain.providers.LocationProvider
import pl.ccki.szypwyp.domain.repositories.SearchConfigRepository
import javax.inject.Inject

class GetCameraCommand @Inject constructor(
    private val cameraPersistence: CameraPersistence,
    private val schedulersProvider: SchedulersProvider
) : Command<Camera> {

    override fun execute(): Observable<Camera> {
        return cameraPersistence.get().applySchedulers(schedulersProvider)
    }
}
