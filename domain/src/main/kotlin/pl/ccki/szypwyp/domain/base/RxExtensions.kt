package pl.ccki.szypwyp.domain.base

import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun Disposable.disposeIn(disposeBag: CompositeDisposable) {
    disposeBag.add(this)
}

fun Command<Unit>.execute(): Completable = execute(Unit)
