package com.example.onlineshop.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlineshop.data.models.Latest
import com.example.onlineshop.databinding.ListItemLatestBinding

class BrandsAdapter: ListAdapter<Latest, BrandsAdapter.BrandsViewHolder>(DiffCallback) {

    private lateinit var context: Context

    class BrandsViewHolder(private var binding: ListItemLatestBinding
    ): RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(latest: Latest){
            Glide.with(binding.root).load(latest.image_url).into(binding.latestImage)
            binding.tvName.text = latest.name
            binding.tvPrice.text = "$ ${latest.price}"
            binding.tvCategory.text = latest.category
        }

    }
    companion object DiffCallback: DiffUtil.ItemCallback<Latest>() {
        override fun areItemsTheSame(oldItem: Latest, newItem: Latest): Boolean {
            return oldItem.image_url == newItem.image_url && oldItem.name == newItem.name &&
                    oldItem.price == newItem.price && oldItem.category == newItem.category
        }

        override fun areContentsTheSame(oldItem: Latest, newItem: Latest): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandsAdapter.BrandsViewHolder {
        context = parent.context
        return BrandsAdapter.BrandsViewHolder(
            ListItemLatestBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: BrandsAdapter.BrandsViewHolder, position: Int) {
        val current = getItem(position)
//        holder.itemView.setOnClickListener {
//
//        }
        holder.bind(current)
    }
}