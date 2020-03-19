package com.flywith24.singlefragment

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

/**
 * 最简单的fragment使用
 * 使用 adb shell setprop log.tag.FragmentManager VERBOSE 命令
 * 并过滤 "FragmentManager" 查看log
 */
class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(TAG, "MainActivity onCreate")
        super.onCreate(savedInstanceState)
        //解决旋转屏幕等场景 fragment 重叠的问题
        if (savedInstanceState == null) {

            //最简单的fragment流程，可使用命令 adb shell setprop log.tag.FragmentManager VERBOSE
            //并过滤 "FragmentManager" 查看log ，更方便地查看整个流程

            //1. 获取 FragmentManager 实例化其实现类 FragmentManagerImpl (空实现)
            //FragmentActivity 中  FragmentController 的 mHost(FragmentHostCallback) 的 mFragmentManager
            //FragmentController → mHost → mFragmentManager → new FragmentManagerImpl()
            supportFragmentManager
                //2. 开启事务 实例化其实现类 BackStackRecord
                // 并将 FragmentManager对象通过构造器传入
                .beginTransaction()
                //3. 所有的操作(add/replace/remove/show/hide/attach/detach)
                // 最终都调用  addOp(new Op(opcmd, fragment)) →   mOps.add(op);
                .add(R.id.container, BlankFragment.newInstance())
                //4. 可选 mAddToBackStack = true;
                .addToBackStack(null) //返回栈这里先不讨论
                //5. BackStackRecord中实现具体逻辑，同类型的方法有
                //commit commitAllowingStateLoss
                //commitNow commitNowAllowingStateLoss
                .commit()
        }
    }

    override fun onStart() {
        Log.i(TAG, "MainActivity onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.i(TAG, "MainActivity onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.i(TAG, "MainActivity onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.i(TAG, "MainActivity onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.i(TAG, "MainActivity onDestroy")
        super.onDestroy()
    }

    companion object {
        const val TAG = "FragmentManager"
    }
}
