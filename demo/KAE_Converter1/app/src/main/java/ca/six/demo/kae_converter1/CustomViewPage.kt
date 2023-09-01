package ca.six.demo.kae_converter1

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.actv_custom_view.btnStart
import kotlinx.android.synthetic.main.actv_custom_view.viewOverlay

class CustomViewPage: AppCompatActivity(R.layout.actv_custom_view) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        btnStart.setOnClickListener {
            viewOverlay.visibility = View.VISIBLE
        }
    }

}

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