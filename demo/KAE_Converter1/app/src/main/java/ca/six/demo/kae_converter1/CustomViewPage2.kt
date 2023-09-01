package ca.six.demo.kae_converter1

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import ca.six.demo.kae_converter1.extensions.dpToPx
import kotlinx.android.synthetic.main.view_ios_switch.view.ivSwitchBg
import kotlinx.android.synthetic.main.view_ios_switch.view.mtlaySwitch

class CustomViewPage2: AppCompatActivity(R.layout.actv_ios_switch) {
}

class SwitchIosView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    val greenBg = GradientDrawable()
    val grayBg = GradientDrawable()
    var isActive = true

    init {
        LayoutInflater.from(context).inflate(R.layout.view_ios_switch, this, true)

        greenBg.cornerRadius = 30f.dpToPx()
        greenBg.setColor(Color.parseColor("#1DC457"))
        grayBg.cornerRadius = 30f.dpToPx()
        grayBg.setColor(Color.parseColor("#D5D6D7"))

        ivSwitchBg.setImageDrawable(greenBg)

        ivSwitchBg.setOnClickListener {
            if(isActive){
                mtlaySwitch.transitionToEnd()
            } else {
                mtlaySwitch.transitionToStart()
            }

            val src = if (isActive) grayBg else greenBg
            ivSwitchBg.setImageDrawable(src)

            isActive = !isActive
        }



    }

}