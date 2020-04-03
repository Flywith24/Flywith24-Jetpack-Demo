package com.flywith24.demo_mergeadapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val mAdapter = MyAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val list = listOf(
            "user1", "user2", "user3",
            "user4", "user5", "user6",
            "user7", "user8", "user9",
            "user10", "user11", "user12"
        )
        recyclerView.apply {
            adapter = mAdapter
        }
        mAdapter.submitList(list)

    }


    class MyAdapter : ListAdapter<String, MyAdapter.MyViewHolder>(DiffCallBack()) {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
            MyViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_username, parent, false)
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
}
