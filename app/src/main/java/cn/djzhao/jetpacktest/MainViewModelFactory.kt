package cn.djzhao.jetpacktest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * MainViewModel的Factory
 *
 * @author djzhao
 * @Date 20/12/28
 */
class MainViewModelFactory(private var countReserved: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainVIewModel(countReserved) as T
    }
}