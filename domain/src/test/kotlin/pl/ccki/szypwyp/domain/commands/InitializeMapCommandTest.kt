package pl.ccki.szypwyp.domain.commands

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.argThat
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.inOrder
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.notNull
import com.nhaarman.mockitokotlin2.stub
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import pl.ccki.szypwyp.domain.TestSchedulers
import pl.ccki.szypwyp.domain.base.execute
import pl.ccki.szypwyp.domain.errors.Android
import pl.ccki.szypwyp.domain.models.Camera
import pl.ccki.szypwyp.domain.models.LatLng
import pl.ccki.szypwyp.domain.models.Permission
import pl.ccki.szypwyp.domain.persistences.CameraPersistence
import pl.ccki.szypwyp.domain.providers.LocationProvider
import pl.ccki.szypwyp.domain.repositories.SearchConfigRepository

class InitializeMapCommandTest {

    lateinit var query: InitializeMapCommand
    lateinit var cameraPersistence: CameraPersistence
    lateinit var locationProvider: LocationProvider
    lateinit var searchConfig: SearchConfigRepository
    lateinit var refreshVehiclesCommand: RefreshVehiclesCommand

    @Before
    fun setUp() {
        cameraPersistence = mock {
            on { update(any()) } doReturn Completable.complete()
        }
        locationProvider = mock()
        searchConfig = mock()
        refreshVehiclesCommand = mock {
            on { execute(any()) } doReturn Completable.complete()
        }
        query = InitializeMapCommand(
            cameraPersistence = cameraPersistence,
            locationProvider = locationProvider,
            searchConfig = searchConfig,
            schedulersProvider = TestSchedulers,
            refreshVehiclesCommand = refreshVehiclesCommand
        )
    }

    @Test
    fun `updates default value on first run`() {
        locationProvider.stub {
            on { singleUpdate(Schedulers.trampoline()) } doReturn Single.error(Android.MissingPermission(Permission.Location))
        }

        query.execute().test().assertNoErrors()

        verify(cameraPersistence).update(any())
    }

    @Test
    fun `emits last saved value instantly and than current users`() {
        locationProvider.stub {
            on { singleUpdate(any()) } doReturn Single.just(LatLng(21.37, 9.11))
        }
        searchConfig.stub {
            on { target } doReturn LatLng(9.12, 21.38)
        }

        query.execute().test().assertNoErrors()

        inOrder(cameraPersistence) {
            verify(cameraPersistence).update(argThat {
                this is Camera.ToPosition &&
                    position == LatLng(9.12, 21.38)
            })
            verify(cameraPersistence).update(argThat {
                this is Camera.ToPosition &&
                    position == LatLng(21.37, 9.11)
            })
        }
    }

    @Test
    fun `emits only last saved value if user has no location`() {
        locationProvider.stub {
            on { singleUpdate(any()) } doReturn Single.error(Android.MissingPermission(Permission.Location))
        }
        searchConfig.stub {
            on { target } doReturn LatLng(9.12, 21.38)
        }

        query.execute().test().assertNoErrors()

        inOrder(cameraPersistence) {
            verify(cameraPersistence).update(argThat {
                this is Camera.ToPosition &&
                    position == LatLng(9.12, 21.38)
            })
        }
    }

    @Test
    fun `updates default target on first run`() {
        locationProvider.stub {
            on { singleUpdate(any()) } doReturn Single.error(Android.MissingPermission(Permission.Location))
        }

        query.execute().test().assertNoErrors()

        verify(searchConfig).target = notNull()
    }
}
