package pl.ccki.szypwyp.vozilla.presentation

import android.content.res.ColorStateList
import android.widget.ImageView
import com.nhaarman.mockitokotlin2.inOrder
import com.nhaarman.mockitokotlin2.mock
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class VozillaAdaptersKtTest {

    @Test
    fun `check first bar`() {
        val view = mock<ImageView>()

        bindBatteryPercentageColor(view, 0, 0)
        bindBatteryPercentageColor(view, 5, 0)
        bindBatteryPercentageColor(view, 10, 0)
        bindBatteryPercentageColor(view, 50, 0)
        bindBatteryPercentageColor(view, 51, 0)

        inOrder {
            verify(view).imageTintList = ColorStateList.valueOf(R.color.vozilla_red)
            verify(view).imageTintList = ColorStateList.valueOf(R.color.vozilla_red)
            verify(view).imageTintList = ColorStateList.valueOf(R.color.vozilla_red)
            verify(view).imageTintList = ColorStateList.valueOf(R.color.vozilla_yellow)
            verify(view).imageTintList = ColorStateList.valueOf(R.color.vozilla_green)
            verify()
        }
    }

    @Test
    fun `check last bar`() {
        val view = mock<ImageView>()

        bindBatteryPercentageColor(view, 5, 4)
        bindBatteryPercentageColor(view, 69, 4)
        bindBatteryPercentageColor(view, 70, 4)
        bindBatteryPercentageColor(view, 71, 4)
        bindBatteryPercentageColor(view, 100, 4)

        inOrder {
            verify(view).imageTintList = ColorStateList.valueOf(R.color.vozilla_grey)
            verify(view).imageTintList = ColorStateList.valueOf(R.color.vozilla_grey)
            verify(view).imageTintList = ColorStateList.valueOf(R.color.vozilla_grey)
            verify(view).imageTintList = ColorStateList.valueOf(R.color.vozilla_green)
            verify(view).imageTintList = ColorStateList.valueOf(R.color.vozilla_green)
            verify()
        }
    }
}