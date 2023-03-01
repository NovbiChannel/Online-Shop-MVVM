package com.example.onlineshop.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlineshop.R
import com.example.onlineshop.data.models.FlashSale
import com.example.onlineshop.databinding.ListItemFlashSaleBinding
import kotlinx.android.synthetic.main.list_item_flash_sale.view.*

class FlashSaleAdapter: RecyclerView.Adapter<FlashSaleAdapter.FlashSaleViewHolder>() {

    inner class FlashSaleViewHolder(view: View):RecyclerView.ViewHolder(view)

    private var _binding: ListItemFlashSaleBinding? = null
    private val binding get() = _binding!!

    private val callback = object : DiffUtil.ItemCallback<FlashSale>() {
        override fun areItemsTheSame(oldItem: FlashSale, newItem: FlashSale): Boolean {
            return oldItem.category == newItem.category && oldItem.name == newItem.name &&
                    oldItem.price == newItem.price && oldItem.image_url == newItem.image_url &&
                    oldItem.discount == newItem.discount
        }

        override fun areContentsTheSame(oldItem: FlashSale, newItem: FlashSale): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FlashSaleAdapter.FlashSaleViewHolder {
        return FlashSaleViewHolder (
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_flash_sale, parent, false)
                )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(
        holder: FlashSaleAdapter.FlashSaleViewHolder,
        position: Int) {
        val flashSale = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(flashSale.image_url).into(binding.ivFlashImage)
            binding.tvCategory.text = flashSale.category
            binding.tvName.text = flashSale.name
            binding.tvPrice.text = "$ ${flashSale.price}"
            binding.tvDiscount.text = "${flashSale.discount}% off"

            setOnClickListener {
                onItemClickListener?.let { it(flashSale) }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((FlashSale) -> Unit)? = null

    fun setOnItemClickListener(listener: (FlashSale) -> Unit) {
        onItemClickListener = listener
    }
}