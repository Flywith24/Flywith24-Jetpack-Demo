package com.flywith24.demo_paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

/**
 * @author yyz (杨云召)
 * @date   2020/5/9
 * time   11:47
 * description
 */
class MyAdapter : PagedListAdapter<Api.Data, MyViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Api.Data>() {
            override fun areItemsTheSame(oldItem: Api.Data, newItem: Api.Data): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Api.Data, newItem: Api.Data): Boolean {
                return oldItem.title == newItem.title
            }
        }
    }
}

class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val title = itemView.findViewById<AppCompatTextView>(R.id.title)
    fun bindTo(data: Api.Data?) {
        data?.let {
            title.text = data.title
        }
    }
}