package kmitl.taweewong.kuad.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import kmitl.taweewong.kuad.R
import kmitl.taweewong.kuad.models.Bottle
import kmitl.taweewong.kuad.viewholders.MyBottleViewHolder

class MyBottleAdapter(private val bottles: ArrayList<Bottle>): RecyclerView.Adapter<MyBottleViewHolder>() {

    override fun getItemCount(): Int {
        return bottles.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyBottleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.my_bottle_layout, parent, false)

        return MyBottleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyBottleViewHolder, position: Int) {
        holder.myBottleItemTitle.text = bottles[position].bottleTitle
        holder.myBottleItemCreatedDate.text = bottles[position].createdAt
    }
}