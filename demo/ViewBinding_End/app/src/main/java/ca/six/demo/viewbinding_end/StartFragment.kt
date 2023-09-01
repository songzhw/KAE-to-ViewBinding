package ca.six.demo.viewbinding_end

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ca.six.demo.viewbinding_end.databinding.FragStartBinding
import ca.six.demo.viewbinding_end.extensions.replaceFragment

class StartFragment: Fragment(R.layout.frag_start) {
    private var binding: FragStartBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        if(view != null) {
            binding = FragStartBinding.bind(view)
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.btnNext?.setOnClickListener {
            val actv = this.activity as? AppCompatActivity ?: return@setOnClickListener
            actv.replaceFragment(EndFragment(), true)
        }
    }
}