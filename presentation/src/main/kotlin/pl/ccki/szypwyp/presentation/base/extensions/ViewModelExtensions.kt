package pl.ccki.szypwyp.presentation.base.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import pl.ccki.szypwyp.domain.base.disposeIn

inline fun <T> LiveData<T>.observe(lifecycleOwner: LifecycleOwner, crossinline function: (T?) -> Unit) =
    observe(lifecycleOwner, Observer { function(it) })

fun <T> Observable<T>.toLiveData(disposeBag: CompositeDisposable): LiveData<T> {
    val mutableLiveData = MutableLiveData<T>()
    observeOn(AndroidSchedulers.mainThread())
        .subscribe {
            mutableLiveData.value = it
        }
        .disposeIn(disposeBag)
    return mutableLiveData
}
