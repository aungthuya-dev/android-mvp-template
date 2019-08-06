package com.aungthuya.codetest.ui.wonders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aungthuya.codetest.R
import com.aungthuya.codetest.model.Wonder
import com.aungthuya.codetest.util.load
import kotlinx.android.synthetic.main.item_wonder.view.*

/**
 * This adapter handle to show the all wonder places in RecyclerView.
 *
 * @author Aung Thuya
 * @since 28 July 2019
 */
class WondersAdapter(private val listener: ((Wonder) -> Unit)? = null) :
    androidx.recyclerview.widget.RecyclerView.Adapter<WondersAdapter.AccountViewHolder>() {

    private var items = mutableListOf<Wonder>()

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        val item = items[position]

        holder.itemView.ivImage.load(item.imageUrl)
        holder.itemView.tvDescription.text = item.description

        holder.itemView.setOnClickListener { listener?.invoke(item) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_wonder, parent, false)
        return AccountViewHolder(view)
    }

    /**
     * Set wonder places to show in recycler view.
     *
     * @param wonders Wonder places.
     */
    fun setItems(wonders: List<Wonder>) {
        items = wonders.toMutableList()
        notifyDataSetChanged()
    }

//    fun removeItem(position: Int) {
//        items.removeAt(position)
//        notifyItemRemoved(position)
//    }
//
//    fun getItem(position: Int): Wonder {
//        return items[position]
//    }

    class AccountViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view)
}