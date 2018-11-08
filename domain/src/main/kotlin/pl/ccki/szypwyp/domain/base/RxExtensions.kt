package pl.ccki.szypwyp.domain.base

import io.reactivex.Completable
import io.reactivex.disposables.Disposable
import io.reactivex.internal.disposables.DisposableContainer

fun Disposable.disposeIn(disposeBag: DisposableContainer) {
    disposeBag.add(this)
}

fun Command<Unit>.execute(): Completable = execute(Unit)
