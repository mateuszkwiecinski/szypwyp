package pl.ccki.szypwyp.domain.commands

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.argWhere
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.stub
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Completable
import io.reactivex.Maybe
import org.junit.Before
import org.junit.Test
import pl.ccki.szypwyp.domain.TestSchedulers
import pl.ccki.szypwyp.domain.models.PluginId
import pl.ccki.szypwyp.domain.persistences.FiltersPersistence
import pl.ccki.szypwyp.domain.repositories.FiltersRepository

class UpdateFilterCommandTest {

    private lateinit var command: UpdateFilterCommand
    private lateinit var repository: FiltersRepository
    private lateinit var persistence: FiltersPersistence

    @Before
    fun setUp() {
        repository = mock()
        persistence = mock {
            on { updateDisabled(any()) } doReturn Completable.complete()
        }
        command = UpdateFilterCommand(repository, persistence, TestSchedulers)
    }

    @Test
    fun `should enable filter`() {
        persistence.stub {
            on { disabled() } doReturn Maybe.just<Set<PluginId>>(setOf(
                PluginId("12"),
                PluginId("13"),
                PluginId("1")
            ))
        }

        val test = command.execute(UpdateFilterCommand.Param(PluginId("1"), true)).test()

        test.awaitTerminalEvent()
        test.assertNoErrors()
        verify(persistence).updateDisabled(argWhere {
            it.count() == 2 &&
                it.any { it.id == "12" } &&
                it.any { it.id == "13" }
        })
        verify(repository).disabled = argWhere {
            it.count() == 2 &&
                it.any { it.id == "12" } &&
                it.any { it.id == "13" }
        }
    }

    @Test
    fun `should disable filter`() {
        persistence.stub {
            on { disabled() } doReturn Maybe.just<Set<PluginId>>(setOf(PluginId("12"), PluginId("13")))
        }

        val test = command.execute(UpdateFilterCommand.Param(PluginId("1"), false)).test()

        test.awaitTerminalEvent()
        test.assertNoErrors()
        verify(persistence).updateDisabled(argWhere {
            it.count() == 3 &&
                it.any { it.id == "12" } &&
                it.any { it.id == "13" } &&
                it.any { it.id == "1" }
        })
        verify(repository).disabled = argWhere {
            it.count() == 3 &&
                it.any { it.id == "12" } &&
                it.any { it.id == "13" } &&
                it.any { it.id == "1" }
        }
    }

    @Test
    fun `should enable filter in wrong state just in case`() {
        persistence.stub {
            on { disabled() } doReturn Maybe.just<Set<PluginId>>(setOf(PluginId("12"), PluginId("13")))
        }

        val test = command.execute(UpdateFilterCommand.Param(PluginId("1"), true)).test()

        test.awaitTerminalEvent()
        test.assertNoErrors()
        verify(persistence).updateDisabled(argWhere {
            it.count() == 2 &&
                it.any { it.id == "12" } &&
                it.any { it.id == "13" }
        })
        verify(repository).disabled = argWhere {
            it.count() == 2 &&
                it.any { it.id == "12" } &&
                it.any { it.id == "13" }
        }
    }

    @Test
    fun `should disable filter in wrong state just in case`() {
        persistence.stub {
            on { disabled() } doReturn Maybe.just<Set<PluginId>>(setOf(PluginId("12"), PluginId("13"), PluginId("1")))
        }

        val test = command.execute(UpdateFilterCommand.Param(PluginId("1"), false)).test()

        test.awaitTerminalEvent()
        test.assertNoErrors()
        verify(persistence).updateDisabled(argWhere {
            it.count() == 3 &&
                it.any { it.id == "12" } &&
                it.any { it.id == "13" } &&
                it.any { it.id == "1" }
        })
        verify(repository).disabled = argWhere {
            it.count() == 3 &&
                it.any { it.id == "12" } &&
                it.any { it.id == "13" } &&
                it.any { it.id == "1" }
        }
    }
}
