package cl.acorrea.inventoried.Interface

import com.acorrea.inventoried.entity.Timer

interface TimerCallback {
    fun nextActivity(timer: Timer)
    {

    }
    fun onTimerTick(timer: Timer)
    {

    }
}