package ca.six.demo.viewbinding_end

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ca.six.demo.viewbinding_end.databinding.ActvMultiUseBinding

//import kotlinx.android.synthetic.main.actv_multi_use.btnChangeTitle
//import kotlinx.android.synthetic.main.actv_multi_use.viewTitle
//import kotlinx.android.synthetic.main.view_top_view.view.tvTitle

class CustomViewPage3: AppCompatActivity(R.layout.actv_multi_use) {
    private lateinit var binding: ActvMultiUseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActvMultiUseBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.viewTitle.binding.tvTitle.text = "hello"

        binding.btnChangeTitle.setOnClickListener {
            binding.viewTitle.binding.tvTitle.text = "world"
        }
    }
}

/*
binding.viewTitle.tvTitle.text = "hello"
现在viewTitle没有tvTitle这一项了
 */
