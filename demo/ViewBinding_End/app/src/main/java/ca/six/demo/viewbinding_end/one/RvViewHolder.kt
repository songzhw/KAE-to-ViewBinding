package ca.six.demo.viewbinding_end.one

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.collection.SparseArrayCompat
import androidx.recyclerview.widget.RecyclerView
import coil.load

class RvViewHolder private constructor(private val convertView: View) :
        RecyclerView.ViewHolder(convertView) {
    private val views: SparseArrayCompat<View> = SparseArrayCompat()

    fun <T : View> getView(id: Int): T {
        var view: View? = views.get(id)
        if (view == null) {
            view = convertView.findViewById(id)
            views.put(id, view)
        }
        return view as T
    }

    fun <T : View> rootView(): T = convertView as T

    // ============================================
    fun setText(id: Int, str: String) {
        val tv = getView<TextView>(id)
        tv.text = str
    }

    fun setSrc(id: Int, drawId: Int) {
        val iv = getView<ImageView>(id)
        iv.setImageResource(drawId)
    }

    fun setImage(id: Int, url: String){
        val iv = getView<ImageView>(id)
        iv.load(url)
    }

    fun setImage(id: Int, uri: Uri){
        val iv = getView<ImageView>(id)
        iv.load(uri)
    }


    fun setBackground(id: Int, bgResId: Int) {
        val view = getView<View>(id)
        view.setBackgroundResource(bgResId)
    }

    fun setBackgroundColor(id: Int, colorInt: Int) {
        val view = getView<View>(id)
        view.setBackgroundColor(colorInt)
    }

    fun setClickListener(id: Int, listener: View.OnClickListener) {
        val view = getView<View>(id)
        view.setOnClickListener(listener)
    }

    fun setVisibility(id: Int, visibility: Int) {
        val view = getView<View>(id)
        view.visibility = visibility
    }


    companion object {
        fun createViewHolder(itemView: View): RvViewHolder {
            return RvViewHolder(itemView)
        }

        fun createViewHolder(parent: ViewGroup, layoutId: Int): RvViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
            return RvViewHolder(itemView)
        }
    }

}
