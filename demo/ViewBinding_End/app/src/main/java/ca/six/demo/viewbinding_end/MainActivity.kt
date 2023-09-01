package ca.six.demo.viewbinding_end

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import ca.six.demo.viewbinding_end.databinding.ActivityMainBinding
import ca.six.demo.viewbinding_end.extensions.nav

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.btn1.text = "Rv"
        binding.btn1.setOnClickListener { nav<RvPage>() }
        binding.btn2.text = "CustomView"
        binding.btn2.setOnClickListener { nav<CustomViewPage>() }
        binding.btn3.text = "CustomView2"
        binding.btn3.setOnClickListener { nav<CustomViewPage2>()  }
        binding.btn4.text = "CustomView3"
        binding.btn4.setOnClickListener { nav<CustomViewPage3>() }
        binding.btn5.text = "Fragments"
        binding.btn5.setOnClickListener { nav<FragmentBoxPage>() }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

}