package com.flywith24.demo_concatadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * @author yyz (杨云召)
 * @date   2020/4/3
 * time   17:02
 * description
 */
class HeaderAdapter : RecyclerView.Adapter<HeaderAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_header, parent, false)
        )

    override fun getItemCount(): Int = 1

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

    }

    class MyViewHolder(item: View) : RecyclerView.ViewHolder(item) {

    }
}