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
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import pl.ccki.szypwyp.domain.base.SchedulersProvider
import pl.ccki.szypwyp.domain.base.execute
import pl.ccki.szypwyp.domain.models.CityId
import pl.ccki.szypwyp.domain.models.CityModel
import pl.ccki.szypwyp.domain.models.AppId
import pl.ccki.szypwyp.domain.models.Kilometers
import pl.ccki.szypwyp.domain.models.LatLng
import pl.ccki.szypwyp.domain.models.LoadEvent
import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.domain.models.PluginId
import pl.ccki.szypwyp.domain.persistences.MapEventsPersistence
import pl.ccki.szypwyp.domain.persistences.VehiclesPersistence
import pl.ccki.szypwyp.domain.repositories.SearchConfigRepository
import pl.ccki.szypwyp.domain.repositories.ServicesConfigurationRepository
import pl.ccki.szypwyp.domain.services.ExternalPlugin

class RefreshVehiclesCommandTest {

    lateinit var configuration: ServicesConfigurationRepository
    lateinit var persistence: VehiclesPersistence
    lateinit var searchConfig: SearchConfigRepository
    lateinit var schedulersProvider: SchedulersProvider
    lateinit var mapEvents: MapEventsPersistence
    private val point45_45 = LatLng(45.0, 45.0)
    private val point0_0 = LatLng(.0, .0)
    private val RANGE_MAX = Kilometers(Long.MAX_VALUE)

    @Before
    fun setUp() {
        configuration = mock {
            on { selected } doReturn null as Iterable<PluginId>?
        }
        persistence = mock {
            on { update(any(), any()) } doReturn Completable.complete()
        }
        searchConfig = mock {
            on { target } doReturn null as LatLng?
        }
        schedulersProvider = mock {
            on { worker } doReturn Schedulers.trampoline()
            on { postExecution } doReturn Schedulers.trampoline()
        }
        mapEvents = mock {
            on { events() } doReturn Observable.create { }
        }
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
        searchConfig.stub {
            on { target } doReturn point45_45
        }
        val registeredPlugins = mapOf(first, second)
        val usecase = RefreshVehiclesCommand(
            configuration,
            registeredPlugins,
            persistence,
            searchConfig,
            mapEvents,
            schedulersProvider
        )

        val test = usecase.execute().test()

        test.awaitTerminalEvent()
        test.assertNoErrors()
        verify(mapEvents).update(argWhere { it is LoadEvent.Finished.WithError && it.id.id == "2" })
        verify(persistence).update(first.first, data)
        verifyNoMoreInteractions(persistence)
    }

    @Test
    fun `should search only in closest cities`() {
        val data = listOf<MarkerModel>(mock(), mock())
        searchConfig.stub {
            on { target } doReturn LatLng(44.9, 44.9)
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
            configuration,
            registeredPlugins,
            persistence,
            searchConfig,
            mapEvents,
            schedulersProvider
        )

        val test = usecase.execute().test()

        test.awaitTerminalEvent()
        verify(persistence).update(second.first, data)
        verifyNoMoreInteractions(persistence)
    }

    @Test
    fun `should emit no elements if no cities found`() {
        val data = listOf<MarkerModel>(mock(), mock())
        searchConfig.stub {
            on { target } doReturn LatLng(0.0, 0.0)
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
            configuration,
            registeredPlugins,
            persistence,
            searchConfig,
            mapEvents,
            schedulersProvider
        )

        val test = usecase.execute().test()

        test.awaitTerminalEvent()
        verifyNoMoreInteractions(persistence)
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
        searchConfig.stub {
            on { target } doReturn point45_45
        }
        val registeredPlugins = mapOf(first, second, third)
        val usecase = RefreshVehiclesCommand(
            configuration,
            registeredPlugins,
            persistence,
            searchConfig,
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
        searchConfig.stub {
            on { target } doReturn point45_45
        }
        val registeredPlugins = mapOf(first, second, third, fourth)
        val usecase = RefreshVehiclesCommand(
            configuration,
            registeredPlugins,
            persistence,
            searchConfig,
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
}

private fun createPlugin(pluginId: String, locations: List<CityModel>, data: () -> List<MarkerModel>): Pair<PluginId,
    ExternalPlugin> {
    val first = object : PluginId {
        override val id: String
            get() = pluginId
    }
    val second = object : ExternalPlugin {
        override val appId: AppId
            get() = AppId("pl.$pluginId")
        override val supportedCities: Iterable<CityModel>
            get() = locations

        override fun findInLocation(location: Iterable<CityId>) = data()
    }

    return first to second
}

data class Id(val value: String) : CityId
