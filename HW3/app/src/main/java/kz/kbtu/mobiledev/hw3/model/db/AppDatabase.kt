package kz.kbtu.mobiledev.hw3.model.db

import android.content.Context
import androidx.room3.Database
import androidx.room3.Room
import androidx.room3.RoomDatabase
import kz.kbtu.mobiledev.hw3.model.dao.ArticleDao
import kz.kbtu.mobiledev.hw3.model.entity.Article

@Database(entities = [Article::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao

    companion object {
        private var Instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, AppDatabase::class.java, "hw3_db").fallbackToDestructiveMigration().build().also { Instance = it }
            }
        }
    }
}