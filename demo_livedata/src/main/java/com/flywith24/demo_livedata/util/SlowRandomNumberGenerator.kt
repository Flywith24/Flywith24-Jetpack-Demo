package com.flywith24.demo_livedata.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.*
import java.util.concurrent.Executors


/**
 * @author yyz (杨云召)
 * @date   2020/3/31
 * time   10:31
 * description
 */
class SlowRandomNumberGenerator {
    private val rnd = Random()

    fun getNumber(): LiveData<Int> {
        val result = MutableLiveData<Int>()

        // Send a random number after a while
        Executors.newSingleThreadExecutor().execute {
            Thread.sleep(500)
            result.postValue(rnd.nextInt(1000))
        }

        return result
    }
}