package ca.six.demo.viewbinding_end.views

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import ca.six.demo.viewbinding_end.R
import ca.six.demo.viewbinding_end.databinding.ViewIosSwitchBinding
import ca.six.demo.viewbinding_end.extensions.dpToPx

class SwitchIosView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    val greenBg = GradientDrawable()
    val grayBg = GradientDrawable()
    var isActive = true

    init {
        val inflater = getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = ViewIosSwitchBinding.inflate(inflater, this, true)  //binding.root是一个motlinLayout, 即来自于xml

        /*
        val binding = ViewIosSwitchBinding.inflate(inflater) //binding.root是一个motlinLayout, 即来自于xml
        this.addView(binding.root)
        这两行代码也行, 就是少了layout params的东东了
         */

        // layoutInflater.inflate()return的是:  If root was supplied and attachToRoot is true, this is root;
        // otherwise it is the root of the inflated XML file.

        greenBg.cornerRadius = 30f.dpToPx()
        greenBg.setColor(Color.parseColor("#1DC457"))
        grayBg.cornerRadius = 30f.dpToPx()
        grayBg.setColor(Color.parseColor("#D5D6D7"))

        binding.ivSwitchBg.setImageDrawable(greenBg)

        binding.ivSwitchBg.setOnClickListener {
            if(isActive){
                binding.mtlaySwitch.transitionToEnd()
            } else {
                binding.mtlaySwitch.transitionToStart()
            }

            val src = if (isActive) grayBg else greenBg
            binding.ivSwitchBg.setImageDrawable(src)

            isActive = !isActive
        }

    }

}