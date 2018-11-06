package pl.ccki.szypwyp.presentation.map.listeners

import android.view.animation.Animation

class AnimationListener(
    private val onAnimationRepeat: ((Animation) -> Unit) = {},
    private val onAnimationEnd: ((Animation) -> Unit) = {},
    private val onAnimationStart: ((Animation) -> Unit) = {}
) : Animation.AnimationListener {
    override fun onAnimationRepeat(animation: Animation) = onAnimationRepeat.invoke(animation)
    override fun onAnimationEnd(animation: Animation) = onAnimationEnd.invoke(animation)
    override fun onAnimationStart(animation: Animation) = onAnimationStart.invoke(animation)
}
