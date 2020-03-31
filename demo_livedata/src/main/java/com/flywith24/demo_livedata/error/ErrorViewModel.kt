package com.flywith24.demo_livedata.error

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.flywith24.demo_livedata.util.SlowRandomNumberGenerator

/**
 * @author yyz (杨云召)
 * @date   2020/3/31
 * time   10:29
 * description
 * LiveData 错误用法
 */
class ErrorViewModel : ViewModel() {
    //错误用法
    lateinit var errorRandomNumber: LiveData<Int>
    private val numberGenerator = SlowRandomNumberGenerator()

    fun onGetNumber() {
        // 每次都赋值一个新的实例
        errorRandomNumber = Transformations.map(numberGenerator.getNumber()) {
            it
        }
    }
}