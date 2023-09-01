package ca.six.demo.kae_converter1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ca.six.demo.kae_converter1.extensions.replaceFragment

class FragmentBoxPage: AppCompatActivity(R.layout.actv_frags) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.replaceFragment(StartFragment(), false)
    }

}