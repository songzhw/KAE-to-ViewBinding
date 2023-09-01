package ca.six.demo.kae_converter1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import ca.six.demo.kae_converter1.extensions.nav
import kotlinx.android.synthetic.main.activity_main.btn1
import kotlinx.android.synthetic.main.activity_main.btn2
import kotlinx.android.synthetic.main.activity_main.btn3
import kotlinx.android.synthetic.main.activity_main.btn4

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        btn1.text = "Rv"
        btn1.setOnClickListener { nav<RvPage>() }
        btn2.text = "CustomView"
        btn2.setOnClickListener { nav<CustomViewPage>() }
        btn3.text = "CustomView2"
        btn3.setOnClickListener {  }
        btn4.text = "Fragments"
        btn4.setOnClickListener { nav<FragmentBoxPage>() }
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