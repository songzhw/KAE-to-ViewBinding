package ca.six.demo.kae_converter1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.actv_ios_switch.tvTitle

class CustomViewPage2 : AppCompatActivity(R.layout.actv_ios_switch) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tvTitle.text = "block Ad?"
    }
}

