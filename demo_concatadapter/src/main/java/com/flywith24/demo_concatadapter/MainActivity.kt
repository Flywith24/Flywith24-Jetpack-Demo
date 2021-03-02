package com.flywith24.demo_concatadapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ConcatAdapter
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
        val header = HeaderAdapter()
        recyclerView.apply {
            adapter = ConcatAdapter(header, mAdapter)
        }
        mAdapter.submitList(list)
    }
}
