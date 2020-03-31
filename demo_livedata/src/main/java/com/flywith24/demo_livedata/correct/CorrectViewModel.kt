package com.flywith24.demo_livedata.correct

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.flywith24.demo_livedata.util.Event
import com.flywith24.demo_livedata.util.SlowRandomNumberGenerator

/**
 * @author yyz (杨云召)
 * @date   2020/3/31
 * time   10:29
 * description
 */
class CorrectViewModel : ViewModel() {
    private val newNumberEvent = MutableLiveData<Event<Any>>()
    private val numberGenerator = SlowRandomNumberGenerator()

    val correctRandomNumber: LiveData<Int> = Transformations.switchMap(newNumberEvent) {
        numberGenerator.getNumber()
    }

    /**
     * Notifies the event LiveData of a new request for a random number.
     */
    fun onGetNumber() {
        newNumberEvent.value = Event(Unit)
    }
}