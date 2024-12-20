package com.ead.test.example.pruebatecnicaandroidiguanadigital.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ead.test.example.pruebatecnicaandroidiguanadigital.data.model.DataItem
import com.ead.test.example.pruebatecnicaandroidiguanadigital.databinding.ItemDataBinding

class ItemAdapter : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    private val items = mutableListOf<DataItem>()
    private var onItemClickListener: ((DataItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemDataBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
        holder.itemView.setOnClickListener { onItemClickListener?.invoke(item) }
    }

    override fun getItemCount(): Int = items.size

    fun submitList(newList: List<DataItem>) {
        val diffResult = androidx.recyclerview.widget.DiffUtil.calculateDiff(
            DiffUtil(items, newList)
        )
        items.clear()
        items.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }

    fun setOnItemClickListener(listener: (DataItem) -> Unit) { onItemClickListener = listener }

    inner class ItemViewHolder(private val binding: ItemDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DataItem) {
            binding.textViewName.text = item.name
        }
    }
}
