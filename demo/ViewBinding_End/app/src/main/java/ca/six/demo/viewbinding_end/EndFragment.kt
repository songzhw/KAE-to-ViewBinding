package ca.six.demo.viewbinding_end

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ca.six.demo.viewbinding_end.databinding.FragEndBinding
import ca.six.demo.viewbinding_end.extensions.nav
//import kotlinx.android.synthetic.main.frag_end.ivSea

class EndFragment : Fragment(R.layout.frag_end) {
    private var binding: FragEndBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        if (view != null) {
            binding = FragEndBinding.bind(view)
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.ivSea?.setOnClickListener {
            val actv = this.activity ?: return@setOnClickListener
            actv.nav<RvPage>()
        }
    }
}