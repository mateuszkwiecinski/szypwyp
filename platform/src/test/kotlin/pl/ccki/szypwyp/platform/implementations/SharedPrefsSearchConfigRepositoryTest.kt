package pl.ccki.szypwyp.platform.implementations

import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import pl.ccki.szypwyp.domain.models.LatLng
import pl.ccki.szypwyp.platform.implementations.sharedprefs.SharedPrefsSearchConfigRepository

@RunWith(RobolectricTestRunner::class)
class SharedPrefsSearchConfigRepositoryTest {

    lateinit var prefs: SharedPrefsSearchConfigRepository

    @Before
    fun setUp() {
        prefs = SharedPrefsSearchConfigRepository(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun canSaveAndReadToStorage() {
        val data = LatLng(12.34, 21.37)
        val initialValue = prefs.target

        prefs.target = data

        assertNull(initialValue)
        assertEquals(data, prefs.target)
    }
}
