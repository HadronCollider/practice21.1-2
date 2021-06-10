package com.makentoshe.androidgithubcitemplate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ElementMainListAdapter(private val ItemClickListener: OnItemClickListener): RecyclerView.Adapter<EMLViewHolder>() {

    private val itemsMainList = mutableListOf<ElementMainList>()

    fun addItem(item: ElementMainList) {
        itemsList.add(item)
        notifyDataSetChanged()
    }

    fun removeItem(id: String) {
        val itemToRemove: ElementMainList? = itemsList.find { it.id == id }
        itemsList.remove(itemToRemove)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_person, parent, false) //здесь в видео после View стоит !
        return EMLViewHolder(itemView, itemClickListener)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.bind(itemsMainList[position])
    }

    override fun getItemCount(): Int {
        return itemsMainList.size
    }


interface onItemClickListener {
    fun onItemClicked(id: String)
}

}