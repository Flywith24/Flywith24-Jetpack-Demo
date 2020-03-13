package com.flywith24.fragment.stack.common

import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.viewbinding.ViewBinding
import com.flywith24.fragment.R
import com.flywith24.library.base.BaseFragment

/**
 * @author yyz (杨云召)
 * @date   2020/3/13
 * time   13:50
 * description
 */
abstract class BaseStackFragment<T : ViewBinding>(@LayoutRes layoutId: Int) :
    BaseFragment<T>(layoutId) {

    val mStackIds = intArrayOf(R.id.stack_1, R.id.stack_2, R.id.stack_3, R.id.stack_4)

    fun name(@IdRes id: Int) = when (id) {
        R.id.stack_1 -> "♥"
        R.id.stack_2 -> "♦"
        R.id.stack_3 -> "♠"
        R.id.stack_4 -> "♣"
        else -> ""
    }

    fun getCount(value: Int): String = when (value) {
        11 -> "J"
        12 -> "Q"
        13 -> "K"
        1 -> "A"
        else -> value.toString()
    }
}