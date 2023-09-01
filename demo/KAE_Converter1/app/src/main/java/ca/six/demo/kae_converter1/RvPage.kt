package ca.six.demo.kae_converter1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ca.six.adv23.rv.adapters.one.OneAdapter
import ca.six.adv23.rv.adapters.one.RvViewHolder
import kotlinx.android.synthetic.main.actv_rv.*

class RvPage : AppCompatActivity(R.layout.actv_rv) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val data = arrayListOf(
            R.drawable.wallpaper01, R.drawable.wallpaper02, R.drawable.wallpaper03, R.drawable.wallpaper04,
            R.drawable.wallpaper01, R.drawable.wallpaper02, R.drawable.wallpaper03, R.drawable.wallpaper04,
            R.drawable.wallpaper01, R.drawable.wallpaper02, R.drawable.wallpaper03, R.drawable.wallpaper04,
            R.drawable.wallpaper01, R.drawable.wallpaper02, R.drawable.wallpaper03, R.drawable.wallpaper04,
        )
        val mgr = LinearLayoutManager(rvLandscape.context)
        rvLandscape.layoutManager = mgr
        rvLandscape.adapter = object : OneAdapter<Int>(R.layout.item_parallax_iv, data) {
            override fun apply(vh: RvViewHolder, value: Int, position: Int) {
                vh.setSrc(R.id.ivParallax, value)
                vh.setText(R.id.btnParallax, "item $position")
            }
        }

        btn0.setOnClickListener {
            rvLandscape.smoothScrollToPosition(0)
        }

        btn5.setOnClickListener {
            rvLandscape.scrollToPosition(5)
        }
    }
}