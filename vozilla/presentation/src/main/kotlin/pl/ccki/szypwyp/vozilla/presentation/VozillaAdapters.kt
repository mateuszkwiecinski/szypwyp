package pl.ccki.szypwyp.vozilla.presentation

import android.content.res.ColorStateList
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter

@BindingAdapter("percent", "index")
fun bindBatteryPercentageColor(view: ImageView, percentage: Int, index: Int) {
    when (percentage) {
        in 0..10 -> when (index) {
            0 -> R.color.vozilla_red
            else -> R.color.vozilla_grey
        }
        in 10..30 -> when (index) {
            0, 1 -> R.color.vozilla_yellow
            else -> R.color.vozilla_grey
        }
        in 30..50 -> when (index) {
            in 0..2 -> R.color.vozilla_yellow
            else -> R.color.vozilla_grey
        }
        in 50..70 -> when (index) {
            in 0..3 -> R.color.vozilla_green
            else -> R.color.vozilla_grey
        }
        in 70..100 -> R.color.vozilla_green
        else -> R.color.vozilla_grey
    }
        .let { ContextCompat.getColor(view.context, it) }
        .let(ColorStateList::valueOf)
        .let(view::setImageTintList)
}
