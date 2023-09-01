package ca.six.demo.viewbinding_end.one

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

// OneAdapter适用于普通RV, VP
// OneLoopAdapter则适合于那种无限循环的carousel, 即size要无限大
abstract class OneLoopAdapter<T> : RecyclerView.Adapter<RvViewHolder> {
    private var layoutResId: Int = 0
    var data: List<T> = arrayListOf()

    constructor(layoutResId: Int) {
        this.layoutResId = layoutResId
        data = ArrayList()
    }

    constructor(layoutResId: Int, data: List<T>) {
        this.layoutResId = layoutResId
        this.data = data
    }

    fun getMidIndex(): Int = Math.ceil(itemCount / 2.0).toInt()

    override fun getItemCount(): Int = if(data.size == 1) 1 else Int.MAX_VALUE

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViewHolder {
        return RvViewHolder.createViewHolder(parent, layoutResId)
    }

    override fun onBindViewHolder(holder: RvViewHolder, position: Int) {
        val index = getIndexOf(position)
        apply(holder, data[index], index)
    }

    private fun getIndexOf(position: Int) = position % data.size

    protected abstract fun apply(vh: RvViewHolder, value: T, index: Int)

}