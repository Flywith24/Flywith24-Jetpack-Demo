package com.flywith24.demo_viewmodel

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

/**
 * @author yyz(杨云召)
 * @date   2020/3/21
 * time   17:01
 * description
 */
class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val mViewModel by viewModels<MyViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //打印activity 与 ViewModel 观察屏幕旋转时二者的值是否变化
        Log.i(TAG, "onCreate: $this $mViewModel")
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
