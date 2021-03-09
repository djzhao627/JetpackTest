package cn.djzhao.jetpacktest

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Book
 *
 * @author djzhao
 * @date 21/03/10
 */
@Entity
data class Book(var name: String, var pages: Int, var author: String) {
    @PrimaryKey
    var id: Long = 0
}