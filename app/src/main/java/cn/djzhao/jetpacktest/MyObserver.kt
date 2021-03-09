package cn.djzhao.jetpacktest

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * 生命周期观察者
 *
 * @author djzhao
 * @Date 20/12/29
 */
class MyObserver(val lifeCycle: Lifecycle) : LifecycleObserver {
    // 传入Lifecycle的目的是为了主动获取目标的生命周期：lifeCycle.currentState

    private val TAG = "MyObserver"

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun activityStart() {

        Log.d(TAG, "activityStart")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun activityStop() {
        Log.d(TAG, "activityStop")
    }
}