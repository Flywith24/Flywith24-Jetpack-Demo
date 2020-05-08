package com.flywith24.demo_paging

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.paging.toLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val liveList: LiveData<PagedList<Api.Data>> by lazy {
        MyDataFactory(lifecycleScope).toLiveData(30)
    }
    private val mAdapter: MyAdapter by lazy { MyAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recyclerView.adapter = mAdapter
        liveList.observe(this, Observer {
            mAdapter.submitList(it)
        })
    }

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
}

