package ca.six.demo.viewbinding_end

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ca.six.demo.viewbinding_end.extensions.replaceFragment

class StartFragment: Fragment(R.layout.frag_start) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnNext.setOnClickListener {
            val actv = this.activity as? AppCompatActivity ?: return@setOnClickListener
            actv.replaceFragment(EndFragment(), true)
        }
    }
}