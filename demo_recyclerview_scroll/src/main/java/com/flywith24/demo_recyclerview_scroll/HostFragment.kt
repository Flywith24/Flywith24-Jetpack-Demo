package com.flywith24.demo_recyclerview_scroll

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_host.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @author yyz (杨云召)
 * @date   2020/5/12
 * time   9:17
 * description
 */
class HostFragment : Fragment(R.layout.fragment_host) {
    private val mAdapter by lazy { MyAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        recyclerView.adapter = mAdapter
        mAdapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY

        // 该策略需与上两个策略配合使用
/*        mAdapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT*/

        // 立即加载数据
//        mAdapter.submitList(buildData())


        // 延迟加载数据
        lifecycleScope.launch {
            val source = loadData()

            mAdapter.submitList(source)
        }
    }

    private suspend fun loadData(): List<String> = withContext(Dispatchers.IO) {
        delay(2000)
        buildData()
    }

    private fun buildData(): List<String> {
        return listOf(
            "title1",
            "title2",
            "title3",
            "title4",
            "title5",
            "title6",
            "title7",
            "title8",
            "title9",
            "title10",
            "title11",
            "title12",
            "title13",
            "title14",
            "title15",
            "title16",
            "title17",
            "title18",
            "title19",
            "title20",
            "title21",
            "title22",
            "title23",
            "title24"
        )
    }

    inner class MyAdapter : ListAdapter<String, MyAdapter.ViewHolder>(object :
        DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
            oldItem == newItem

    }) {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val itemView =
                LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
            val title = itemView.findViewById<AppCompatTextView>(R.id.title)
            title.setOnClickListener {
                parentFragmentManager.commit {
                    replace<DetailFragment>(
                        R.id.container,
                        args = Bundle().apply {
                            putString("arg", title.text.toString())
                        })
                    addToBackStack(null)
                }
            }
            return ViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(getItem(position))
        }

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val title: AppCompatTextView = itemView.findViewById(R.id.title)

            fun bind(item: String?) {
                title.text = item
            }
        }
    }
}