package ca.six.demo.viewbinding_end

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ca.six.demo.viewbinding_end.databinding.ActvFragsBinding
import ca.six.demo.viewbinding_end.databinding.ActvMultiUseBinding
import ca.six.demo.viewbinding_end.extensions.replaceFragment

class FragmentBoxPage: AppCompatActivity(R.layout.actv_frags) {
    private lateinit var binding: ActvFragsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActvFragsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        this.replaceFragment(StartFragment(), false)
    }

}