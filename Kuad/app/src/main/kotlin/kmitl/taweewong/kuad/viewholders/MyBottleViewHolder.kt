package kmitl.taweewong.kuad.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import kmitl.taweewong.kuad.R

class MyBottleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val myBottleItemTitle: TextView = itemView.findViewById(R.id.myBottleItemTitle)
    val myBottleItemCreatedDate: TextView = itemView.findViewById(R.id.myBottleItemCreatedDate)
}