package pl.ccki.szypwyp.presentation.interfaces

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import pl.ccki.szypwyp.domain.models.MarkerModel

interface MapViewsProvider {

    fun createIcon(context: Context, marker: MarkerModel): Bitmap?

    fun createInfoWindow(inflater: LayoutInflater, marker: MarkerModel): View?
}

fun Int.generateBitmap(context: Context) =
    ContextCompat.getDrawable(context, this)?.let(::getMarkerIconFromDrawable)

fun getMarkerIconFromDrawable(drawable: Drawable): Bitmap {
    val canvas = Canvas()
    val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
    canvas.setBitmap(bitmap)
    drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
    drawable.draw(canvas)
    return bitmap
}
