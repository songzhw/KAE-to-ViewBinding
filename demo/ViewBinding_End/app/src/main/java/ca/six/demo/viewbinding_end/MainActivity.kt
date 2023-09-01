package ca.six.demo.viewbinding_end

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import ca.six.demo.kae_converter1.extensions.nav
import ca.six.demo.viewbinding_end.R

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        btn1.text = "Rv"
        btn1.setOnClickListener { nav<RvPage>() }
        btn2.text = "CustomView"
        btn2.setOnClickListener { nav<CustomViewPage>() }
        btn3.text = "CustomView2"
        btn3.setOnClickListener { nav<CustomViewPage2>()  }
        btn4.text = "CustomView3"
        btn4.setOnClickListener { nav<CustomViewPage3>() }
        btn5.text = "Fragments"
        btn5.setOnClickListener { nav<FragmentBoxPage>() }
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