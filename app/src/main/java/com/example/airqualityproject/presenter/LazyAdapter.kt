package com.example.airqualityproject.presenter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.airqualityproject.databinding.LazyAdapterBinding
import com.example.airqualityproject.domain.model.Data

class LazyAdapter : ListAdapter<Data, LazyAdapter.ItemViewHolder>(LazyCallback()) {

    class ItemViewHolder private constructor (val binding: LazyAdapterBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(
            item: Data
        ) {
            binding.rvAdapter.text = item.station.name
            binding.executePendingBindings()

        }

        companion object {
            fun from(parent: ViewGroup): ItemViewHolder {
                val binding = LazyAdapterBinding.inflate(
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
        holder.bind(item)
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