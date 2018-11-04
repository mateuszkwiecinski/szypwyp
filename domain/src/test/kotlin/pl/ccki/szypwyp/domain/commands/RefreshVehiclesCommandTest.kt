package pl.ccki.szypwyp.domain.commands

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.stub
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import pl.ccki.szypwyp.domain.base.SchedulersProvider
import pl.ccki.szypwyp.domain.base.execute
import pl.ccki.szypwyp.domain.models.CityId
import pl.ccki.szypwyp.domain.models.CityModel
import pl.ccki.szypwyp.domain.models.ExternalAppId
import pl.ccki.szypwyp.domain.models.Kilometers
import pl.ccki.szypwyp.domain.models.LatLng
import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.domain.models.PluginId
import pl.ccki.szypwyp.domain.persistences.VehiclesPersistence
import pl.ccki.szypwyp.domain.repositories.SearchConfigRepository
import pl.ccki.szypwyp.domain.repositories.ServicesConfigurationRepository
import pl.ccki.szypwyp.domain.services.ExternalPlugin

class RefreshVehiclesCommandTest {

    lateinit var configuration: ServicesConfigurationRepository
    lateinit var persistence: VehiclesPersistence
    lateinit var searchConfig: SearchConfigRepository
    lateinit var schedulersProvider: SchedulersProvider
    private val point45_45 = LatLng(45.0, 45.0)
    private val point0_0 = LatLng(.0, .0)
    private val RANGE_MAX = Kilometers(Int.MAX_VALUE)

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
            on { target } doReturn LatLng(100.0, 100.0)
        }
        val registeredPlugins = mapOf(first, second)
        val usecase = RefreshVehiclesCommand(
            configuration,
            registeredPlugins,
            persistence,
            searchConfig,
            schedulersProvider
        )

        val test = usecase.execute().test()

        test.awaitTerminalEvent()
        test.assertError(IllegalStateException::class.java)
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
            schedulersProvider
        )

        val test = usecase.execute().test()

        test.awaitTerminalEvent()
        verifyNoMoreInteractions(persistence)
    }

    private fun createPlugin(pluginId: String, locations: List<CityModel>, data: () -> List<MarkerModel>): Pair<PluginId,
        ExternalPlugin> {
        val first = object : PluginId {
            override val id: String
                get() = pluginId
        }
        val second = object : ExternalPlugin {
            override val appId: ExternalAppId
                get() = ExternalAppId("pl.$pluginId")
            override val supportedCities: Iterable<CityModel>
                get() = locations

            override fun findInLocation(location: CityId) = data()
        }

        return first to second
    }
}

data class Id(val value: String) : CityId
