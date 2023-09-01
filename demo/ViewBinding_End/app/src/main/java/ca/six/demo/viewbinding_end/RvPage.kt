package ca.six.demo.viewbinding_end

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ca.six.demo.viewbinding_end.databinding.ActvRvBinding
import ca.six.demo.viewbinding_end.one.OneAdapter
import ca.six.demo.viewbinding_end.one.RvViewHolder

class RvPage : AppCompatActivity() {
    private lateinit var binding: ActvRvBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActvRvBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = arrayListOf(
            R.drawable.wallpaper01, R.drawable.wallpaper02, R.drawable.wallpaper03, R.drawable.wallpaper04,
            R.drawable.wallpaper01, R.drawable.wallpaper02, R.drawable.wallpaper03, R.drawable.wallpaper04,
            R.drawable.wallpaper01, R.drawable.wallpaper02, R.drawable.wallpaper03, R.drawable.wallpaper04,
            R.drawable.wallpaper01, R.drawable.wallpaper02, R.drawable.wallpaper03, R.drawable.wallpaper04,
        )
        val mgr = LinearLayoutManager(binding.rvLandscape.context)
        binding.rvLandscape.layoutManager = mgr
        binding.rvLandscape.adapter = object : OneAdapter<Int>(R.layout.item_parallax_iv, data) {
            override fun apply(vh: RvViewHolder, value: Int, position: Int) {
                vh.setSrc(R.id.ivParallax, value)
                vh.setText(R.id.btnParallax, "item $position")
            }
        }

        binding.btn0.setOnClickListener {
            binding.rvLandscape.smoothScrollToPosition(0)
        }

        binding.btn5.setOnClickListener {
            binding.rvLandscape.scrollToPosition(5)
        }
    }
}