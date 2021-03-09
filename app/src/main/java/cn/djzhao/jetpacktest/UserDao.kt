package cn.djzhao.jetpacktest

import androidx.room.*

/**
 * UserDao
 *
 * @author djzhao
 * @date 21/03/10
 */
@Dao
interface UserDao {
    @Insert
    fun insertUser(user: User) : Long

    @Update
    fun updateUser(user: User)

    @Query("select * from User")
    fun loadAllUsers() : List<User>

    @Query("select * from User where age > :age")
    fun loadUsersOlderThan(age: Int) : List<User>

    @Delete
    fun deleteUser(user: User)

    @Query("delete from User where lastName = :lastName")
    fun deleteByLastName(lastName: String): Int
}