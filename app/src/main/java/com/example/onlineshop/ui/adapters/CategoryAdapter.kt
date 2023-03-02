package com.example.onlineshop.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlineshop.data.models.Category
import com.example.onlineshop.databinding.ListItemCategoryBinding

class CategoryAdapter(): ListAdapter<Category, CategoryAdapter.CategoryViewHolder>(DiffCallback) {

    private lateinit var context: Context

    class CategoryViewHolder(private var binding: ListItemCategoryBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(category: Category){
            Glide.with(binding.root).load(category.imgSrc).into(binding.ivCategory)
            binding.tvName.text = category.name
        }

    }
    companion object DiffCallback: DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.imgSrc == newItem.imgSrc && oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        context = parent.context
        return CategoryViewHolder(
            ListItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val current = getItem(position)
//        holder.itemView.setOnClickListener {
//
//        }
        holder.bind(current)
    }

}

