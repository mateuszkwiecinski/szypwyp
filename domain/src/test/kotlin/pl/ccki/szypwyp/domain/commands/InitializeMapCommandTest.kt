package pl.ccki.szypwyp.domain.commands

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.argThat
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.inOrder
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.stub
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import pl.ccki.szypwyp.domain.TestSchedulers
import pl.ccki.szypwyp.domain.base.execute
import pl.ccki.szypwyp.domain.errors.Android
import pl.ccki.szypwyp.domain.models.Camera
import pl.ccki.szypwyp.domain.models.DEFAULT_LOCATION
import pl.ccki.szypwyp.domain.models.LatLng
import pl.ccki.szypwyp.domain.models.Permission
import pl.ccki.szypwyp.domain.persistences.CameraPersistence
import pl.ccki.szypwyp.domain.persistences.CurrentSearchTargetPersistence
import pl.ccki.szypwyp.domain.persistences.PotentialSearchTargetPersistence
import pl.ccki.szypwyp.domain.providers.LocationProvider
import pl.ccki.szypwyp.domain.repositories.SearchConfigRepository

class InitializeMapCommandTest {

    lateinit var query: InitializeMapCommand
    lateinit var cameraPersistence: CameraPersistence
    lateinit var locationProvider: LocationProvider
    lateinit var potentialTarget: PotentialSearchTargetPersistence
    lateinit var refreshVehiclesCommand: RefreshVehiclesCommand
    lateinit var currentTarget: CurrentSearchTargetPersistence

    var target: LatLng? = null

    @Before
    fun setUp() {
        target = null
        cameraPersistence = mock {
            on { update(any()) } doReturn Completable.complete()
        }
        locationProvider = mock()
        potentialTarget = mock {
            on { update(any()) } doReturn Completable.complete()
            on { get() } doReturn Observable.empty()
        }
        refreshVehiclesCommand = mock {
            on { execute(any()) } doReturn Completable.complete()
        }
        currentTarget = mock {
            on { current() } doReturn Maybe.fromCallable<LatLng> { target }
            on { update(any()) } doAnswer {
                val arg = it.arguments.first() as LatLng
                Completable.fromAction {
                    target = arg
                }
            }
        }
        query = InitializeMapCommand(
            cameraPersistence = cameraPersistence,
            locationProvider = locationProvider,
            potentialTarget = potentialTarget,
            schedulersProvider = TestSchedulers,
            refreshVehiclesCommand = refreshVehiclesCommand,
            currentTarget = currentTarget
        )
    }

    @Test
    fun `updates default value on first run`() {
        locationProvider.stub {
            on { singleUpdate(Schedulers.trampoline()) } doReturn Single.error(Android.MissingPermission(Permission.Location))
        }

        val test = query.execute().test()

        test.awaitTerminalEvent()
        test.assertNoErrors()
        verify(cameraPersistence).update(any())
        verify(potentialTarget).update(any())
    }

    @Test
    fun `emits last saved value instantly and than current users`() {
        locationProvider.stub {
            on { singleUpdate(any()) } doReturn Single.just(LatLng(21.37, 9.11))
        }
        target = LatLng(9.12, 21.38)

        val test = query.execute().test()

        test.awaitTerminalEvent()
        test.assertNoErrors()
        cameraPersistence.inOrder {
            verify().update(argThat {
                this is Camera.ToPosition &&
                    position == LatLng(9.12, 21.38)
            })
            verify().update(argThat {
                this is Camera.ToPosition &&
                    position == LatLng(21.37, 9.11)
            })
        }

        verify(potentialTarget).update(LatLng(21.37, 9.11))
    }

    @Test
    fun `emits only last saved value if user has no location`() {
        locationProvider.stub {
            on { singleUpdate(any()) } doReturn Single.error(Android.MissingPermission(Permission.Location))
        }
        target = LatLng(9.12, 21.38)

        val test = query.execute().test()

        test.awaitTerminalEvent()
        test.assertNoErrors()
        verify(cameraPersistence).update(argThat {
            this is Camera.ToPosition &&
                position == LatLng(9.12, 21.38)
        })
        verify(potentialTarget).update(LatLng(9.12, 21.38))
    }

    @Test
    fun `updates default target on first run`() {
        locationProvider.stub {
            on { singleUpdate(any()) } doReturn Single.error(Android.MissingPermission(Permission.Location))
        }

        val test = query.execute().test()

        test.awaitTerminalEvent()
        test.assertNoErrors()
        verify(potentialTarget).update(any())
        verify(currentTarget).update(DEFAULT_LOCATION)
    }
}

class TestConfigRepostitory() : SearchConfigRepository {
    override var target: LatLng? = null
}
