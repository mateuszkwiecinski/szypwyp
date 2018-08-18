package pl.ccki.szypwyp.presentation.base.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

inline fun <T> LiveData<T>.observe(lifecycleOwner: LifecycleOwner, crossinline function: (T?) -> Unit) =
    observe(lifecycleOwner, Observer { function(it) })
