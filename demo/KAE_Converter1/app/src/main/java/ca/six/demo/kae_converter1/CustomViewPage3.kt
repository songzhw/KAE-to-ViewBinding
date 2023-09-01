package ca.six.demo.kae_converter1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.actv_multi_use.btnChangeTitle
import kotlinx.android.synthetic.main.actv_multi_use.viewTitle
import kotlinx.android.synthetic.main.view_top_view.view.tvTitle

class CustomViewPage3: AppCompatActivity(R.layout.actv_multi_use) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewTitle.tvTitle.text = "hello"

        btnChangeTitle.setOnClickListener {
            viewTitle.tvTitle.text = "world"
        }
    }
}
