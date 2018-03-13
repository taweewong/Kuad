package kmitl.taweewong.kuad.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import kmitl.taweewong.kuad.adapters.BottleAdapter.ItemType.MESSAGE
import kmitl.taweewong.kuad.adapters.BottleAdapter.ItemType.TITLE
import kmitl.taweewong.kuad.models.BottleMessageItem
import kmitl.taweewong.kuad.viewholders.BottleTitleViewHolder
import android.view.LayoutInflater
import kmitl.taweewong.kuad.R
import kmitl.taweewong.kuad.viewholders.BottleMessageViewHolder


class BottleAdapter(private val bottleMessageItems: ArrayList<BottleMessageItem>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    object ItemType {
        const val TITLE = 1
        const val MESSAGE = 2
    }

    override fun getItemCount(): Int {
        return bottleMessageItems.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> TITLE
            else -> MESSAGE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return if (viewType == TITLE) {
            val itemView = inflater.inflate(R.layout.bottle_title_layout, parent, false)
            BottleTitleViewHolder(itemView)
        } else {
            val itemView = inflater.inflate(R.layout.bottle_message_layout, parent, false)
            BottleMessageViewHolder(itemView)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewType = getItemViewType(position)

        if (viewType == TITLE) {
            val bottleTitleHolder = holder as BottleTitleViewHolder
            bottleTitleHolder.bottleTitle.text = bottleMessageItems[position].message
        } else {
            val bottleTitleHolder = holder as BottleMessageViewHolder
            bottleTitleHolder.bottleMessage.text = bottleMessageItems[position].message
            bottleTitleHolder.bottleMessageTime.text = bottleMessageItems[position].date
        }
    }
}