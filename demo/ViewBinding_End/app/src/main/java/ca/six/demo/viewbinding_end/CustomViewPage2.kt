package ca.six.demo.viewbinding_end

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ca.six.demo.viewbinding_end.databinding.ActvIosSwitchBinding
// import kotlinx.android.synthetic.main.actv_ios_switch.tvTitle

class CustomViewPage2 : AppCompatActivity(R.layout.actv_ios_switch) {
    private lateinit var binding: ActvIosSwitchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.tvTitle.text = "block Ad?"
    }
}

