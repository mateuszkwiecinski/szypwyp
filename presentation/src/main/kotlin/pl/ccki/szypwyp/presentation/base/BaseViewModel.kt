package pl.ccki.szypwyp.presentation.base

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import pl.ccki.szypwyp.domain.base.Query
import pl.ccki.szypwyp.domain.base.disposeIn

abstract class BaseViewModel : ViewModel(), LifecycleObserver {
    protected val disposeBag = CompositeDisposable()

    override fun onCleared() {
        disposeBag.clear()
        super.onCleared()
    }

    fun <T : Any> Query<T>.execute(action: (T) -> Unit) =
        execute().observeOn(AndroidSchedulers.mainThread()).subscribe(action).disposeIn(disposeBag)
}
