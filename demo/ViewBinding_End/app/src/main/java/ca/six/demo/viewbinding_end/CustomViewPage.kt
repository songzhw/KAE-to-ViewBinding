package ca.six.demo.viewbinding_end

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import ca.six.demo.viewbinding_end.databinding.ActvCustomViewBinding

class CustomViewPage : AppCompatActivity(R.layout.actv_custom_view) {
    private lateinit var binding: ActvCustomViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActvCustomViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener {
            binding.viewOverlay.visibility = View.VISIBLE
        }
    }

}
