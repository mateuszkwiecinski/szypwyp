package pl.ccki.szypwyp.presentation.base

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.internal.disposables.DisposableContainer
import pl.ccki.szypwyp.domain.base.Query

abstract class BaseViewModel : ViewModel(), LifecycleObserver {
    protected val disposeBag = CompositeDisposable()

    override fun onCleared() {
        disposeBag.clear()
        super.onCleared()
    }

    fun <T : Any> Query<T>.execute(action: (T) -> Unit) =
        execute().subscribe(action).disposeWith(disposeBag)
}

fun Disposable.disposeWith(disposeBag: DisposableContainer) =
    disposeBag.add(this)


