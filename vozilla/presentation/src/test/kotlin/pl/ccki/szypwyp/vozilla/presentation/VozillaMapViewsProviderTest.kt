package pl.ccki.szypwyp.vozilla.presentation

import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertNotEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class VozillaMapViewsProviderTest {

    @Test
    fun createIcon() {
        val provider = VozillaMapViewsProvider()

        val icon  = provider.createIcon(ApplicationProvider.getApplicationContext())

        assertNotEquals(null, icon)
    }
}