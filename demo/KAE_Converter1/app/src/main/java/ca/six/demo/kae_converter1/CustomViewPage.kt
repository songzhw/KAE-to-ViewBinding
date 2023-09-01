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
