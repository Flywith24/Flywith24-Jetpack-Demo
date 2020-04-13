package com.flywith24.demo_mergeadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

/**
 * @author yyz (杨云召)
 * @date   2020/4/3
 * time   17:01
 * description
 */
class MyAdapter : ListAdapter<String, MyAdapter.MyViewHolder>(DiffCallBack()) {
    override fun getItemViewType(position: Int): Int =
        if (position % 2 == 0) 0 else 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        if (viewType == 0)
            MyViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_username, parent, false)
            ) else MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_username_other_type, parent, false)
        )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MyViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val username: AppCompatTextView = item.findViewById(R.id.text)

        fun bind(item: String) {
            username.text = item
        }
    }

    class DiffCallBack : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
            oldItem == newItem
    }
}