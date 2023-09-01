package ca.six.demo.kae_converter1

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ca.six.demo.kae_converter1.extensions.nav
import kotlinx.android.synthetic.main.frag_end.ivSea

class EndFragment : Fragment(R.layout.frag_end) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ivSea.setOnClickListener {
            val actv = this.activity ?: return@setOnClickListener
            actv.nav<RvPage>()
        }
    }
}