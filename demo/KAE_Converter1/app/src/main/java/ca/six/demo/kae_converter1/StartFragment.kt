package ca.six.demo.kae_converter1

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ca.six.demo.kae_converter1.extensions.replaceFragment
import kotlinx.android.synthetic.main.frag_start.btnNext

class StartFragment: Fragment(R.layout.frag_start) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnNext.setOnClickListener {
            val actv = this.activity as? AppCompatActivity ?: return@setOnClickListener
            actv.replaceFragment(EndFragment(), true)
        }
    }
}