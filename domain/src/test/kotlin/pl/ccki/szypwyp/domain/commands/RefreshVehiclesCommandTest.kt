package pl.ccki.szypwyp.domain.commands

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.argWhere
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.inOrder
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.stub
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import pl.ccki.szypwyp.domain.base.SchedulersProvider
import pl.ccki.szypwyp.domain.base.execute
import pl.ccki.szypwyp.domain.models.CityModel
import pl.ccki.szypwyp.domain.models.Kilometers
import pl.ccki.szypwyp.domain.models.LatLng
import pl.ccki.szypwyp.domain.models.LoadEvent
import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.domain.models.PluginId
import pl.ccki.szypwyp.domain.persistences.CurrentSearchTargetPersistence
import pl.ccki.szypwyp.domain.persistences.FiltersPersistence
import pl.ccki.szypwyp.domain.persistences.MapEventsPersistence
import pl.ccki.szypwyp.domain.persistences.VehiclesPersistence
import pl.ccki.szypwyp.domain.utils.Id
import pl.ccki.szypwyp.domain.utils.createPlugin

class RefreshVehiclesCommandTest {

    lateinit var filters: FiltersPersistence
    lateinit var vehicles: VehiclesPersistence
    lateinit var currentTarget: CurrentSearchTargetPersistence
    lateinit var schedulersProvider: SchedulersProvider
    lateinit var mapEvents: MapEventsPersistence
    private val point45_45 = LatLng(45.0, 45.0)
    private val point0_0 = LatLng(.0, .0)
    private val RANGE_MAX = Kilometers(Long.MAX_VALUE)

    @Before
    fun setUp() {
        filters = mock {
            on { disabled() } doReturn Maybe.empty()
        }
        vehicles = mock {
            on { update(any(), any()) } doReturn Completable.complete()
        }
        currentTarget = mock {
            on { current() } doReturn Maybe.empty()
        }
        schedulersProvider = mock {
            on { worker } doReturn Schedulers.trampoline()
            on { postExecution } doReturn Schedulers.trampoline()
        }
        mapEvents = mock()
    }

    @Test
    fun `should update values immediately after emit`() {
        val data = listOf<MarkerModel>(mock(), mock())
        val first = createPlugin("1", listOf(CityModel(Id("11"), point45_45, RANGE_MAX))) {
            data
        }
        val second = createPlugin("2", listOf(CityModel(Id("22"), point45_45, RANGE_MAX))) {
            Thread.sleep(100)
            throw IllegalStateException()
        }
        schedulersProvider.stub {
            on { worker } doReturn Schedulers.io()
        }
        currentTarget.stub {
            on { current() } doReturn Maybe.just(point45_45)
        }
        val registeredPlugins = mapOf(first, second)
        val usecase = RefreshVehiclesCommand(
            filters,
            registeredPlugins,
            vehicles,
            currentTarget,
            mapEvents,
            schedulersProvider
        )

        val test = usecase.execute().test()

        test.awaitTerminalEvent()
        test.assertNoErrors()
        verify(mapEvents).update(argWhere { it is LoadEvent.Finished.WithError && it.id.id == "2" })
        verify(vehicles).update(first.first, data)
        verifyNoMoreInteractions(vehicles)
    }

    @Test
    fun `should search only in closest cities`() {
        val data = listOf<MarkerModel>(mock(), mock())
        currentTarget.stub {
            on { current() } doReturn Maybe.just(point45_45)
        }
        val first = createPlugin("2", listOf(CityModel(
            id = Id("22"),
            center = point0_0,
            radius = Kilometers(100)
        ))) {
            throw IllegalStateException()
        }
        val second = createPlugin("1", listOf(CityModel(
            id = Id("11"),
            center = point45_45,
            radius = Kilometers(100)
        ))) {
            data
        }
        val registeredPlugins = mapOf(first, second)
        val usecase = RefreshVehiclesCommand(
            filters,
            registeredPlugins,
            vehicles,
            currentTarget,
            mapEvents,
            schedulersProvider
        )

        val test = usecase.execute().test()

        test.awaitTerminalEvent()
        test.assertNoErrors()
        verify(vehicles).update(second.first, data)
        verifyNoMoreInteractions(vehicles)
    }

    @Test
    fun `should emit no elements if no cities found`() {
        val data = listOf<MarkerModel>(mock(), mock())
        currentTarget.stub {
            on { current() } doReturn Maybe.just(LatLng(0.0, 0.0))
        }
        val first = createPlugin("1", listOf(CityModel(
            id = Id("22"),
            center = point45_45,
            radius = Kilometers(100)
        ))) {
            throw IllegalStateException()
        }
        val second = createPlugin("2", listOf(CityModel(
            id = Id("11"),
            center = point45_45,
            radius = Kilometers(100)
        ))) {
            data
        }
        val registeredPlugins = mapOf(first, second)
        val usecase = RefreshVehiclesCommand(
            filters,
            registeredPlugins,
            vehicles,
            currentTarget,
            mapEvents,
            schedulersProvider
        )

        val test = usecase.execute().test()

        test.awaitTerminalEvent()
        test.assertNoErrors()
        verifyNoMoreInteractions(vehicles)
    }

    @Test
    fun `should emit progress events in right order`() {
        val data = listOf<MarkerModel>(mock(), mock())
        val first = createPlugin("10", listOf(CityModel(Id("910"), point45_45, RANGE_MAX))) {
            Thread.sleep(30)
            data
        }
        val second = createPlugin("20", listOf(CityModel(Id("911"), point45_45, RANGE_MAX))) {
            Thread.sleep(20)
            data
        }
        val third = createPlugin("30", listOf(CityModel(Id("912"), point45_45, RANGE_MAX))) {
            Thread.sleep(10)
            data
        }
        schedulersProvider.stub {
            on { worker } doReturn Schedulers.io()
        }
        currentTarget.stub {
            on { current() } doReturn Maybe.just(point45_45)
        }
        val registeredPlugins = mapOf(first, second, third)
        val usecase = RefreshVehiclesCommand(
            filters,
            registeredPlugins,
            vehicles,
            currentTarget,
            mapEvents,
            schedulersProvider
        )

        val test = usecase.execute().test()

        test.awaitTerminalEvent()
        test.assertNoErrors()
        mapEvents.inOrder {
            verify().update(argWhere { it is LoadEvent.Initial })
            verify(mapEvents, times(3)).update(argWhere { it is LoadEvent.Loading })
            verify().update(argWhere { it is LoadEvent.Finished.WithSuccess && it.id.id == "30" })
            verify().update(argWhere { it is LoadEvent.Finished.WithSuccess && it.id.id == "20" })
            verify().update(argWhere { it is LoadEvent.Finished.WithSuccess && it.id.id == "10" })
            verify().update(argWhere { it is LoadEvent.Completed })
            verifyNoMoreInteractions()
        }
    }

    @Test
    fun `should emit error and progress events in right order`() {
        val data = listOf<MarkerModel>(mock(), mock())
        val first = createPlugin("10", listOf(CityModel(Id("910"), point45_45, RANGE_MAX))) {
            Thread.sleep(40)
            data
        }
        val second = createPlugin("20", listOf(CityModel(Id("911"), point45_45, RANGE_MAX))) {
            Thread.sleep(30)
            data
        }
        val third = createPlugin("30", listOf(CityModel(Id("912"), point45_45, RANGE_MAX))) {
            Thread.sleep(20)
            throw IllegalStateException("TestException")
        }
        val fourth = createPlugin("40", listOf(CityModel(Id("914"), point45_45, RANGE_MAX))) {
            Thread.sleep(10)
            data
        }
        schedulersProvider.stub {
            on { worker } doReturn Schedulers.io()
        }
        currentTarget.stub {
            on { current() } doReturn Maybe.just(LatLng(0.0, 0.0))
        }
        val registeredPlugins = mapOf(first, second, third, fourth)
        val usecase = RefreshVehiclesCommand(
            filters,
            registeredPlugins,
            vehicles,
            currentTarget,
            mapEvents,
            schedulersProvider
        )

        val test = usecase.execute().test()

        test.awaitTerminalEvent()
        test.assertNoErrors()
        mapEvents.inOrder {
            verify().update(argWhere { it is LoadEvent.Initial })
            verify(mapEvents, times(registeredPlugins.count())).update(argWhere { it is LoadEvent.Loading })
            verify().update(argWhere { it is LoadEvent.Finished.WithSuccess && it.id.id == "40" })
            verify().update(argWhere { it is LoadEvent.Finished.WithError && it.id.id == "30" })
            verify().update(argWhere { it is LoadEvent.Finished.WithSuccess && it.id.id == "20" })
            verify().update(argWhere { it is LoadEvent.Finished.WithSuccess && it.id.id == "10" })
            verify().update(argWhere { it is LoadEvent.Completed })
            verifyNoMoreInteractions()
        }
    }

    @Test
    fun `should search all if all plugins filtered`() {
        val data = listOf<MarkerModel>(mock(), mock())
        filters.stub {
            on { disabled() } doReturn Maybe.just<Set<PluginId>>(setOf(Id("2"), Id("1")))
        }
        currentTarget.stub {
            on { current() } doReturn Maybe.just(point45_45)
        }
        val first = createPlugin("2", listOf(CityModel(
            id = Id("22"),
            center = point45_45,
            radius = Kilometers(100)
        ))) { data }
        val second = createPlugin("1", listOf(CityModel(
            id = Id("11"),
            center = point45_45,
            radius = Kilometers(100)
        ))) { data }
        val registeredPlugins = mapOf(first, second)
        val usecase = RefreshVehiclesCommand(
            filters,
            registeredPlugins,
            vehicles,
            currentTarget,
            mapEvents,
            schedulersProvider
        )

        val test = usecase.execute().test()

        test.awaitTerminalEvent()
        test.assertNoErrors()
        verify(vehicles).update(first.first, data)
        verify(vehicles).update(second.first, data)
        verifyNoMoreInteractions(vehicles)
    }

    @Test
    fun `should search in non filtered plugins`() {
        val data = listOf<MarkerModel>(mock(), mock())
        filters.stub {
            on { disabled() } doReturn Maybe.just<Set<PluginId>>(setOf(Id("2")))
        }
        currentTarget.stub {
            on { current() } doReturn Maybe.just(point45_45)
        }
        val first = createPlugin("2", listOf(CityModel(
            id = Id("22"),
            center = point45_45,
            radius = Kilometers(100)
        ))) { data }
        val second = createPlugin("1", listOf(CityModel(
            id = Id("11"),
            center = point45_45,
            radius = Kilometers(100)
        ))) { data }
        val registeredPlugins = mapOf(first, second)
        val usecase = RefreshVehiclesCommand(
            filters,
            registeredPlugins,
            vehicles,
            currentTarget,
            mapEvents,
            schedulersProvider
        )

        val test = usecase.execute().test()

        test.awaitTerminalEvent()
        test.assertNoErrors()
        verify(vehicles).update(second.first, data)
        verifyNoMoreInteractions(vehicles)
    }
}
