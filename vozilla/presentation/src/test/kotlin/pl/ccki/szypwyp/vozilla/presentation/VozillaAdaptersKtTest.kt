package pl.ccki.szypwyp.vozilla.presentation

import android.content.Context
import android.content.res.ColorStateList
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.test.core.app.ApplicationProvider
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.inOrder
import com.nhaarman.mockitokotlin2.mock
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class VozillaAdaptersKtTest {

    @Test
    fun `check first bar`() {
        val view = mock<ImageView> {
            on { context } doReturn ApplicationProvider.getApplicationContext<Context>()
        }

        bindBatteryPercentageColor(view, 0, 0)
        bindBatteryPercentageColor(view, 5, 0)
        bindBatteryPercentageColor(view, 10, 0)
        bindBatteryPercentageColor(view, 50, 0)
        bindBatteryPercentageColor(view, 51, 0)

        view.inOrder {
            verify().imageTintList = R.color.vozilla_red.toColorStateList()
            verify().imageTintList = R.color.vozilla_red.toColorStateList()
            verify().imageTintList = R.color.vozilla_red.toColorStateList()
            verify().imageTintList = R.color.vozilla_yellow.toColorStateList()
            verify().imageTintList = R.color.vozilla_green.toColorStateList()
            Unit
        }
    }

    @Test
    fun `check last bar`() {
        val view = mock<ImageView> {
            on { context } doReturn ApplicationProvider.getApplicationContext<Context>()
        }

        bindBatteryPercentageColor(view, 5, 4)
        bindBatteryPercentageColor(view, 69, 4)
        bindBatteryPercentageColor(view, 70, 4)
        bindBatteryPercentageColor(view, 71, 4)
        bindBatteryPercentageColor(view, 100, 4)

        view.inOrder {
            verify().imageTintList = R.color.vozilla_grey.toColorStateList()
            verify().imageTintList = R.color.vozilla_grey.toColorStateList()
            verify().imageTintList = R.color.vozilla_grey.toColorStateList()
            verify().imageTintList = R.color.vozilla_green.toColorStateList()
            verify().imageTintList = R.color.vozilla_green.toColorStateList()
            Unit
        }
    }
}

private fun Int.toColorStateList() =
    ColorStateList.valueOf(ContextCompat.getColor(ApplicationProvider.getApplicationContext(), this))
