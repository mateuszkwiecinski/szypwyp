package pl.ccki.szypwyp.domain.persistences

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.stub
import org.junit.Before
import org.junit.Test
import pl.ccki.szypwyp.domain.models.PluginId
import pl.ccki.szypwyp.domain.repositories.FiltersRepository

class InMemoryFiltersPersistenceTest {

    private lateinit var persistence: InMemoryFiltersPersistence
    private lateinit var repository: FiltersRepository
    @Before
    fun setUp() {
        repository = mock()
        persistence = InMemoryFiltersPersistence(repository)
    }

    @Test
    fun updateDisabled() {
        repository.stub {
            on { disabled } doReturn setOf(PluginId("one"), PluginId("two"))
        }

        val test = persistence.current().test()

        test.awaitTerminalEvent()
        test.assertValue {
            it.map { it.id }.containsAll(setOf("one", "two"))
        }
    }
}
