package cn.djzhao.jetpacktest

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

/**
 * BookDao
 *
 * @author djzhao
 * @date 21/03/10
 */
@Dao
interface BookDao {

    @Insert
    fun insertBook(book: Book)

    @Query("select * from Book")
    fun loadAllBooks(): List<Book>
}