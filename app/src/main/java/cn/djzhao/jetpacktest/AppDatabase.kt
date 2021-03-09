package cn.djzhao.jetpacktest

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * Database
 *
 * @author djzhao
 * @date 21/03/10
 */
// @Database(version = 1, entities = [User::class])
// 升级1-2：添加新表
// @Database(version = 2, entities = [User::class, Book::class])
// 升级2-3：添加新列
@Database(version = 3, entities = [User::class, Book::class])
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    // 升级1-2
    abstract fun bookDao(): BookDao

    companion object {

        // 升级1-2
        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("create table Book(id integer primary key autoincrement not null, name text not null, pages integer not null)")
            }
        }

        // 升级2-3
        private val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("alter table Book add column author text not null default 'unknown'")
            }
        }

        private var instance: AppDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): AppDatabase {
            instance?.let {
                return it
            }
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "app_database"
            )
                // 允许在主线程操作数据，不推荐使用(Debug时使用)
                .allowMainThreadQueries()
                // 数据库升级时销毁当前数据库，不推荐使用
                .fallbackToDestructiveMigration()
                // 升级1-2、2-3
                .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                .build()
                .apply {
                    instance = this
                }
        }
    }
}