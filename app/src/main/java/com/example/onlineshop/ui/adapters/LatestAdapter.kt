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
import com.example.onlineshop.data.models.Latest
import com.example.onlineshop.databinding.ListItemLatestBinding
import kotlinx.android.synthetic.main.list_item_latest.view.*

class LatestAdapter: RecyclerView.Adapter<LatestAdapter.LatestViewHolder>() {

    inner class LatestViewHolder(view: View):RecyclerView.ViewHolder(view)

    private var _binding: ListItemLatestBinding? = null
    private val binding get() = _binding!!

    private val callback = object : DiffUtil.ItemCallback<Latest>() {
        override fun areItemsTheSame(oldItem: Latest, newItem: Latest): Boolean {
            return oldItem.category == newItem.category && oldItem.name == newItem.name &&
                    oldItem.price == newItem.price && oldItem.image_url == newItem.image_url
        }

        override fun areContentsTheSame(oldItem: Latest, newItem: Latest): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LatestAdapter.LatestViewHolder {
        return LatestViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_latest, parent, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(
        holder: LatestAdapter.LatestViewHolder,
        position: Int) {
        val latest = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(latest.image_url).into(latest_image)
            tv_category.text = latest.category
            tv_name.text = latest.name
            tv_price.text = "$ ${latest.price}"

            setOnClickListener {
                onItemClickListener?.let { it(latest) }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Latest) -> Unit)? = null

    fun setOnItemClickListener(listener: (Latest) -> Unit) {
        onItemClickListener = listener
    }
}