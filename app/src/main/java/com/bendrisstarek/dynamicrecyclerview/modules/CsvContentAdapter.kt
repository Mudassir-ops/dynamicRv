package com.bendrisstarek.dynamicrecyclerview.modules

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.text.HtmlCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bendrisstarek.dynamicrecyclerview.R
import com.bendrisstarek.dynamicrecyclerview.databinding.ItemCsvContentBinding

class CsvContentAdapter(
    private var dataSet: ArrayList<StandardModel>,
    private val topList: ArrayList<String>
) : RecyclerView.Adapter<CsvContentAdapter.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val mBinding: ItemCsvContentBinding? = DataBindingUtil.bind(view)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_csv_content, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val item = dataSet[position]
        val context = viewHolder.mBinding?.root?.context
       viewHolder.mBinding?.layout?.removeAllViews()
        val lst = item.list
        for (i in lst.indices) {
            val content = lst[i]
            val txt = TextView(context)
            val htmlContent = HtmlCompat.fromHtml(
                "<b>" + topList[i] + " :</b>   " + content,
                HtmlCompat.FROM_HTML_MODE_LEGACY
            )
            txt.text = htmlContent
            txt.textSize = 15f
            val face = Typeface.createFromAsset(context?.assets, "fonts/comfortaaregular.ttf")
            txt.typeface = face
            val layoutParams = ConstraintLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            layoutParams.leftToLeft = viewHolder.mBinding?.layout?.left!!
            layoutParams.topToTop = viewHolder.mBinding.layout.top
            txt.layoutParams = layoutParams
            viewHolder.mBinding.layout.addView(txt)
        }

       viewHolder.mBinding?.executePendingBindings()
    }

    override fun getItemCount() = dataSet.size
}
