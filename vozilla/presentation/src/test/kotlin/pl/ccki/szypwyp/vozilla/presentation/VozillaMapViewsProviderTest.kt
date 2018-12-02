package pl.ccki.szypwyp.vozilla.presentation

import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertNotEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import pl.ccki.szypwyp.domain.models.LatLng
import pl.ccki.szypwyp.vozilla.domain.models.VozillaMarkerModel

@RunWith(RobolectricTestRunner::class)
class VozillaMapViewsProviderTest {

    @Test
    fun createIcon() {
        val provider = VozillaMapViewsProvider()

        val icon  = provider.createIcon(ApplicationProvider.getApplicationContext(), VozillaMarkerModel(
            id = "",
            location = LatLng(0.0, 0.0),
            name = "test",
            range = null,
            battery = null
        ))

        assertNotEquals(null, icon)
    }
}
