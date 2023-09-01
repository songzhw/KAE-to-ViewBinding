package ca.six.demo.viewbinding_end.extensions

import android.content.res.Resources

fun Int.dpToPx(): Int {
    return (this * Resources.getSystem().displayMetrics.density).toInt()
}

fun Float.dpToPx(): Float {
    return this * Resources.getSystem().displayMetrics.density
}

fun Int.pxToDp(): Int {
    return (this.toFloat() / Resources.getSystem().displayMetrics.density).toInt()
}