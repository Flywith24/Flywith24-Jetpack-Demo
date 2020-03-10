package com.flywith24.demo_viewpager2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author yyz(杨云召)
 * @date   2020/3/10
 * time   15:31
 * description
 * fragment + viewpager2 demo
 */
class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        pager.adapter = SimplePagerAdapter(this)
        TabLayoutMediator(tabLayout, pager) { tab, position ->
            tab.text = "OBJECT ${(position + 1)}"
        }.attach()
    }

    companion object {
        const val NUM_PAGES = 5
    }

    inner class SimplePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = NUM_PAGES

        override fun createFragment(position: Int): Fragment =
            ItemFragment.newInstance((position + 1).toString())
    }
}
