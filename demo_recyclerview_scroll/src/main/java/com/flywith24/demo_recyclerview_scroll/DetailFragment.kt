package com.flywith24.demo_recyclerview_scroll

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.flywith24.library.base.ext.args
import kotlinx.android.synthetic.main.item_list.*

/**
 * @author yyz (杨云召)
 * @date   2020/5/12
 * time   9:32
 * description
 */
class DetailFragment : Fragment(R.layout.item_list) {
    private val arg: String by args()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        title.text = arg
    }
}