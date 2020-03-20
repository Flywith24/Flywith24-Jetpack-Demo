package com.flywith24.demo_savestate.viewmodelwithsavestate

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.flywith24.demo_savestate.R
import com.flywith24.demo_savestate.databinding.FragmentViewmodelBinding
import com.flywith24.library.base.BaseFragment

/**
 * @author yyz(杨云召)
 * @date   2020/3/19
 * time   22:08
 * description
 * 带有 SavedStateHandle 的 ViewModel 测试
 * 1. 在 EditText 中输入文字，点击保存
 * 2. 在 terminal 中 键入 adb shell ps -A |grep com.flywith24.demo_savestate 查看程序状态
 * 3. 按 home 键将程序至于后台，键入 adb shell am kill com.flywith24.demo_savestate 命令杀掉进程
 * 4. 重复步骤2 查看程序是否被杀死
 * 5. 点击桌面应用程序图标打开应用，界面恢复并且 textView 中有数据（ViewModel内数据被恢复）
 */
class ViewModelWithSavedStateFragment :
    BaseFragment<FragmentViewmodelBinding>(R.layout.fragment_viewmodel) {

    private val mViewModel by viewModels<WithSavedStateViewModel>()

    override fun initBinding(view: View): FragmentViewmodelBinding =
        FragmentViewmodelBinding.bind(view)

    override fun init(savedInstanceState: Bundle?) {
        binding.save.setOnClickListener { mViewModel.setValue(binding.edit.text.toString()) }
        mViewModel.getValue().observe(viewLifecycleOwner) {
            binding.text.text = getString(R.string.saved_in_viewmodel, it)
        }
    }
}
