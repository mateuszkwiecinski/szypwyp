package pl.ccki.szypwyp.presentation.base.databinding

import android.view.View
import androidx.databinding.BindingAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

@BindingAdapter("android:visibility")
fun bindVisibility(view: View, isVisible: Boolean?) {
    when (view) {
        is FloatingActionButton -> {
            if (isVisible == true) {
                view.show()
            } else {
                view.hide()
            }
        }
        else -> {
            view.visibility = if (isVisible == true) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }
}
