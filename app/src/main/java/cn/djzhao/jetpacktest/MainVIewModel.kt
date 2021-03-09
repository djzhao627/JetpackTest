package cn.djzhao.jetpacktest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

/**
 * mainActivity的ViewModel
 *
 * @author djzhao
 * @Date 20/12/28
 */
class MainVIewModel(countReserved: Int) : ViewModel() {

    val counter: LiveData<Int>
        get() = _counter
    private val _counter = MutableLiveData<Int>()


    private val user = MutableLiveData<User>()

    // 外部只能获取用户名，不能获取其他信息(age)也不能操作;使用map进行转换
    val userName: LiveData<String> = Transformations.map(user) {
        "${it.firstName} ${it.lastName}"
    }

    init {
        _counter.value = countReserved
    }

    fun plusOne() {
        val count = _counter.value ?: 0
        _counter.value = count + 1
    }

    fun reset() {
        _counter.value = 0
    }

    // 如果直接调用这个方法进行观察：viewModel.getUser(userId).observe(this){user -> }
    // 那么数据不会发生变化，因为getUser每次都会返回一个新的对象
    /*fun getUser(userId: String): LiveData<User> {
        return Repository.getUser(userId)
    }*/

    private val userIdLiveData = MutableLiveData<String>()

    // 使用switchMap来改善上面的情况
    fun getUser(userId: String) {
        userIdLiveData.value = userId
    }

    val userLiveData:LiveData<User> = Transformations.switchMap(userIdLiveData) {
        Repository.getUser(it)
    }
    // Transformations也是一个观察器userIdLiveData发生变化就会执行
}


object Repository {
    fun getUser(userId: String): LiveData<User> {
        val userLiveData = MutableLiveData<User>()
        userLiveData.value = User("dj-$userId", "zhao", 27)
        return userLiveData
    }
}