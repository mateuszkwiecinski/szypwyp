package pl.ccki.szypwyp.blinkee.presentation

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import pl.ccki.szypwyp.blinkee.domain.models.BlinkeeType

@BindingAdapter("android:src")
fun bindSrc(view: ImageView, value: BlinkeeType?) {
    when (value) {
        BlinkeeType.Scooter -> R.drawable.ic_scooter
        BlinkeeType.Bike -> R.drawable.ic_bike_orange
        null -> null
    }?.let(view::setImageResource) ?: view.setImageDrawable(null)
}

@BindingAdapter("android:text")
fun bindText(view: TextView, value: BlinkeeType?) {
    view.text = when (value) {
        BlinkeeType.Scooter -> R.string.type_scooter
        BlinkeeType.Bike -> R.string.type_bike
        null -> null
    }?.let(view.resources::getText)

}
