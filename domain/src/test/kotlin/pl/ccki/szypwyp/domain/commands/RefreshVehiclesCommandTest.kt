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
import pl.ccki.szypwyp.domain.models.LatLng
import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.domain.models.PluginId
import pl.ccki.szypwyp.domain.persistences.VehiclesPersistence
import pl.ccki.szypwyp.domain.repositories.SearchConfigRepository
import pl.ccki.szypwyp.domain.repositories.ServicesConfigurationRepository
import pl.ccki.szypwyp.domain.services.ExternalPlugin
import java.lang.IllegalStateException

class RefreshVehiclesCommandTest {

    lateinit var configuration: ServicesConfigurationRepository
    lateinit var persistence: VehiclesPersistence
    lateinit var searchConfig: SearchConfigRepository
    lateinit var schedulersProvider: SchedulersProvider

    @Before
    fun setUp() {
        configuration = mock {
            on { selected } doReturn null as Iterable<PluginId>?
        }
        persistence = mock {
            on { update(any(), any()) } doReturn Completable.complete()
        }
        searchConfig = mock()
        schedulersProvider = mock {
            on { worker } doReturn Schedulers.trampoline()
            on { postExecution } doReturn Schedulers.trampoline()
        }
    }

    @Test
    fun `should update values immediately`() {
        val first = object : PluginId {
            override val id = "1"
        }
        val second = object : PluginId {
            override val id = "2"
        }
        val data = listOf<MarkerModel>(mock(), mock())
        val firstPlugin = object : ExternalPlugin {
            override fun findInLocation(location: LatLng): List<MarkerModel> =
                data
        }
        val secondPlugin = object : ExternalPlugin {
            override fun findInLocation(location: LatLng): List<MarkerModel> {
                Thread.sleep(100)
                throw IllegalStateException()
            }
        }
        schedulersProvider.stub {
            on { worker } doReturn Schedulers.io()
        }
        val registeredPlugins = mapOf(first to firstPlugin, second to secondPlugin)
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
        verify(persistence).update(first, data)
        verifyNoMoreInteractions(persistence)
    }
}