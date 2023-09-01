package ca.six.demo.viewbinding_end.extensions

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ca.six.demo.viewbinding_end.R

inline fun <reified T : Activity> Activity.nav(data: Bundle? = null, flags : Int = -1) {
    val it = Intent(this, T::class.java)
    if(data != null) it.putExtras(data)
    if(flags != -1) it.addFlags(flags)
    startActivity(it)
}

fun AppCompatActivity.replaceFragment(frag: Fragment, isRecording: Boolean) {
    val tr = this.supportFragmentManager.beginTransaction()
    if(isRecording) {
        tr.addToBackStack("to ${frag::class.java.name}")
    }
    tr.replace(R.id.host, frag)
    tr.commitAllowingStateLoss()
}