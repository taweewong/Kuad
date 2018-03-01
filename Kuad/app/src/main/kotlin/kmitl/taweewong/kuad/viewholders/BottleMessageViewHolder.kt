package kmitl.taweewong.kuad.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import kmitl.taweewong.kuad.R

class BottleMessageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val bottleMessage: TextView = itemView.findViewById(R.id.bottleMessage)
    val bottleMessageTime: TextView = itemView.findViewById(R.id.bottleMessageTime)
}