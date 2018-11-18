package pl.ccki.szypwyp.domain.queries

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.stub
import io.reactivex.Maybe
import io.reactivex.Observable
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import pl.ccki.szypwyp.domain.TestSchedulers
import pl.ccki.szypwyp.domain.models.CityModel
import pl.ccki.szypwyp.domain.models.FilterState
import pl.ccki.szypwyp.domain.models.Kilometers
import pl.ccki.szypwyp.domain.models.LatLng
import pl.ccki.szypwyp.domain.models.PluginId
import pl.ccki.szypwyp.domain.persistences.CurrentSearchTargetPersistence
import pl.ccki.szypwyp.domain.persistences.FiltersPersistence
import pl.ccki.szypwyp.domain.utils.Id
import pl.ccki.szypwyp.domain.utils.WORLD
import pl.ccki.szypwyp.domain.utils.createPlugin

class GetFiltersQueryTest {

    private lateinit var currentTarget: CurrentSearchTargetPersistence
    private lateinit var filters: FiltersPersistence

    @Before
    fun setUp() {
        currentTarget = mock()
        filters = mock()
    }

    @Test
    fun `should return all enabled if filters are empty`() {
        val first = createPlugin("1", listOf(WORLD)) { emptyList() }
        val second = createPlugin("2", listOf(WORLD)) { emptyList() }
        val third = createPlugin("3", listOf(WORLD)) { emptyList() }
        val plugins = mapOf(first, second, third)
        val query = GetFiltersQuery(plugins, currentTarget, filters, TestSchedulers)
        currentTarget.stub {
            on { get() } doReturn Observable.just(LatLng(45.0, 45.0))
        }
        filters.stub {
            on { disabled() } doReturn Maybe.empty()
        }

        val test = query.execute().test()

        test.awaitTerminalEvent()
        test.assertNoErrors()
        val result = test.values().single()
        assertThat(result).hasSize(3)
        assertThat(result).contains(
            GetFiltersQuery.Item(
                Id("1"),
                FilterState(true)
            ),
            GetFiltersQuery.Item(
                Id("2"),
                FilterState(true)
            ),
            GetFiltersQuery.Item(
                Id("3"),
                FilterState(true)
            )
        )
    }

    @Test
    fun `should return all enabled if filters has all available services`() {
        val first = createPlugin("1", listOf(WORLD)) { emptyList() }
        val second = createPlugin("2", listOf(WORLD)) { emptyList() }
        val third = createPlugin("3", listOf(WORLD)) { emptyList() }
        val plugins = mapOf(first, second, third)
        val query = GetFiltersQuery(plugins, currentTarget, filters, TestSchedulers)
        currentTarget.stub {
            on { get() } doReturn Observable.just(LatLng(45.0, 45.0))
        }
        filters.stub {
            on { disabled() } doReturn Maybe.just<Set<PluginId>>(setOf(Id("2"), Id("1"), Id("3")))
        }

        val test = query.execute().test()

        test.awaitTerminalEvent()
        test.assertNoErrors()
        val result = test.values().single()
        assertThat(result).hasSize(3)
        assertThat(result).contains(
            GetFiltersQuery.Item(
                Id("1"),
                FilterState(true)
            ),
            GetFiltersQuery.Item(
                Id("2"),
                FilterState(true)
            ),
            GetFiltersQuery.Item(
                Id("3"),
                FilterState(true)
            )
        )
    }

    @Test
    fun `should return as enabled only few selected plugins`() {
        val first = createPlugin("1", listOf(WORLD)) { emptyList() }
        val second = createPlugin("2", listOf(WORLD)) { emptyList() }
        val third = createPlugin("3", listOf(WORLD)) { emptyList() }
        val plugins = mapOf(first, second, third)
        val query = GetFiltersQuery(plugins, currentTarget, filters, TestSchedulers)
        currentTarget.stub {
            on { get() } doReturn Observable.just(LatLng(45.0, 45.0))
        }
        filters.stub {
            on { disabled() } doReturn Maybe.just<Set<PluginId>>(setOf(Id("3")))
        }

        val test = query.execute().test()

        test.awaitTerminalEvent()
        test.assertNoErrors()
        val result = test.values().single()
        assertThat(result).hasSize(3)
        assertThat(result).contains(
            GetFiltersQuery.Item(
                Id("1"),
                FilterState(true)
            ),
            GetFiltersQuery.Item(
                Id("2"),
                FilterState(true)
            ),
            GetFiltersQuery.Item(
                Id("3"),
                FilterState(false)
            )
        )
    }

    @Test
    fun `should return as enabled plugins in range`() {
        val first = createPlugin("1", listOf(WORLD)) { emptyList() }
        val third = createPlugin("3", listOf(WORLD)) { emptyList() }
        val second = createPlugin("2", listOf(CityModel(
            Id("notapplicable"),
            center = LatLng(.0, .0),
            radius = Kilometers(1)
        ))) { emptyList() }
        val plugins = mapOf(first, second, third)
        val query = GetFiltersQuery(plugins, currentTarget, filters, TestSchedulers)
        currentTarget.stub {
            on { get() } doReturn Observable.just(LatLng(45.0, 45.0))
        }
        filters.stub {
            on { disabled() } doReturn Maybe.just<Set<PluginId>>(setOf(Id("3")))
        }

        val test = query.execute().test()

        test.awaitTerminalEvent()
        test.assertNoErrors()
        val result = test.values().single()
        assertThat(result).hasSize(2)
        assertThat(result).contains(
            GetFiltersQuery.Item(
                Id("1"),
                FilterState(true)
            ),
            GetFiltersQuery.Item(
                Id("3"),
                FilterState(false)
            )
        )
    }
}
