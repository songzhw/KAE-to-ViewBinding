package ca.six.demo.kae_converter1.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.util.AttributeSet
import android.view.View
import androidx.core.view.ViewCompat.setLayerType

class MyOverlayView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    View(context, attrs, defStyleAttr) {
    private val eraser = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        setLayerType(LAYER_TYPE_SOFTWARE, null)
        eraser.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
        setOnClickListener {
            this.visibility = View.GONE
        }
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawColor(Color.parseColor("#8A000000")) // 半透明背景 Colors.black54
        canvas.drawCircle(140f, 100f, 100f, eraser)
    }
}