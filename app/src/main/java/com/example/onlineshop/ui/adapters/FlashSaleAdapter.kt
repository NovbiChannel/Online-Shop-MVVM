package com.example.onlineshop.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlineshop.data.models.FlashSale
import com.example.onlineshop.databinding.ListItemFlashSaleBinding

class FlashSaleAdapter: ListAdapter<FlashSale, FlashSaleAdapter.FlashSaleViewHolder>(DiffCallback) {

    private lateinit var context: Context

    class FlashSaleViewHolder(private var binding: ListItemFlashSaleBinding
    ): RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(flashSale: FlashSale){
            Glide.with(binding.root).load(flashSale.image_url).into(binding.ivFlashImage)
            binding.tvName.text = flashSale.name
            binding.tvPrice.text = "$ ${flashSale.price}"
            binding.tvCategory.text = flashSale.category
            binding.tvDiscount.text = "${flashSale.discount}% off"
        }

    }
    companion object DiffCallback: DiffUtil.ItemCallback<FlashSale>() {
        override fun areItemsTheSame(oldItem: FlashSale, newItem: FlashSale): Boolean {
            return oldItem.image_url == newItem.image_url && oldItem.name == newItem.name &&
                    oldItem.price == newItem.price && oldItem.category == newItem.category &&
                    oldItem.discount == newItem.discount
        }

        override fun areContentsTheSame(oldItem: FlashSale, newItem: FlashSale): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlashSaleAdapter.FlashSaleViewHolder {
        context = parent.context
        return FlashSaleAdapter.FlashSaleViewHolder(
            ListItemFlashSaleBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: FlashSaleAdapter.FlashSaleViewHolder, position: Int) {
        val current = getItem(position)
//        holder.itemView.setOnClickListener {
//
//        }
        holder.bind(current)
    }
}