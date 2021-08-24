package com.example.airqualityproject.presenter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.airqualityproject.databinding.LazyCityAdapterBinding
import com.example.airqualityproject.domain.model.Data

class CityListAdapter(val clickListener: CityListListener) : ListAdapter<Data, CityListAdapter.ItemViewHolder>(LazyCallback()) {

    class ItemViewHolder private constructor (val binding: LazyCityAdapterBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(
            item: Data,
            clickListener: CityListListener
        ) {
            binding.dataResponse = item
            binding.clickListener = clickListener
            binding.executePendingBindings()

        }

        companion object {
            fun from(parent: ViewGroup): ItemViewHolder {
                val binding = LazyCityAdapterBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return ItemViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder.from(parent)
    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
       val item = getItem(position)
        holder.bind(item, clickListener)
    }

}

class LazyCallback : DiffUtil.ItemCallback<Data>(){
    override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem.uid == newItem.uid
    }

    override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem == newItem
    }
}

class CityListListener(val clickListener: (data: Data) -> Unit){
    fun onClick(data: Data) = clickListener(data)
}

