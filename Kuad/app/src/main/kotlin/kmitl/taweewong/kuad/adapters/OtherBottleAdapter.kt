package kmitl.taweewong.kuad.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import kmitl.taweewong.kuad.R
import kmitl.taweewong.kuad.models.BottleReference
import kmitl.taweewong.kuad.viewholders.OtherBottleViewHolder

class OtherBottleAdapter(private val bottleRefs: ArrayList<BottleReference>): RecyclerView.Adapter<OtherBottleViewHolder>() {

    override fun getItemCount(): Int {
        return bottleRefs.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OtherBottleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.other_bottle_layout, parent, false)

        return OtherBottleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: OtherBottleViewHolder, position: Int) {
        holder.otherBottleItemTitle.text = bottleRefs[position].title
    }
}