package pl.ccki.szypwyp.domain.commands

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.stub
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import pl.ccki.szypwyp.domain.models.LatLng
import pl.ccki.szypwyp.domain.persistences.PotentialSearchTargetPersistence

class UpdatePotentialSearchTargetCommandTest {

    lateinit var command: UpdatePotentialSearchTargetCommand
    lateinit var persistence: PotentialSearchTargetPersistence

    @Before
    fun setUp() {
        persistence = mock {
            on { get() } doReturn Observable.empty()
            on { update(any()) } doReturn Completable.complete()
        }
        command = UpdatePotentialSearchTargetCommand(persistence)
    }

    @Test
    fun `should not update target if new location close to actual one`() {
        val param = LatLng(20.00, 30.0)
        persistence.stub {
            on { last() } doReturn Maybe.create {
                it.onSuccess(LatLng(19.9, 29.9))
            }
            on { locked } doReturn true
        }

        val test = command.execute(param).test()

        test.awaitTerminalEvent()
        test.assertNoErrors().assertComplete()
        verify(persistence, times(0)).update(any())
    }

    @Test
    fun `should update target on distant new value`() {
        val param = LatLng(20.00, 30.0)
        persistence.stub {
            on { last() } doReturn Maybe.create {
                it.onSuccess(LatLng(49.9, 59.9))
            }
            on { locked } doReturn true
        }

        val test = command.execute(param).test()

        test.awaitTerminalEvent()
        test.assertNoErrors().assertComplete()
        verify(persistence).update(LatLng(20.0, 30.0))
    }
}
