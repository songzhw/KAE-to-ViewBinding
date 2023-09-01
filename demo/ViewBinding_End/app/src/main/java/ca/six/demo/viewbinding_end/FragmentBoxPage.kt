package ca.six.demo.viewbinding_end

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ca.six.demo.viewbinding_end.databinding.ActvFragsBinding
import ca.six.demo.viewbinding_end.extensions.replaceFragment

class FragmentBoxPage: AppCompatActivity(R.layout.actv_frags) {
    private lateinit var binding: ActvFragsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.replaceFragment(StartFragment(), false)
    }

}