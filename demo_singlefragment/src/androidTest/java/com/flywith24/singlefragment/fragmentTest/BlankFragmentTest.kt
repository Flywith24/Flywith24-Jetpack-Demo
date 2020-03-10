package com.flywith24.singlefragment.fragmentTest

import androidx.fragment.app.testing.launchFragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.runner.AndroidJUnit4
import com.flywith24.singlefragment.BlankFragment
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @author yyz (杨云召)
 * @date   2020/3/10
 * time   9:17
 * description
 */
@RunWith(AndroidJUnit4::class)
class BlankFragmentTest {
    @Test
    fun testEventFragment() {
        val scenario = launchFragment<BlankFragment>()
        scenario.moveToState(Lifecycle.State.RESUMED)
    }
}