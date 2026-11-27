package kz.kbtu.mobiledev.hw3.model.dao

import androidx.room3.Dao
import androidx.room3.Delete
import androidx.room3.Insert
import androidx.room3.Query
import androidx.room3.Update
import kotlinx.coroutines.flow.Flow
import kz.kbtu.mobiledev.hw3.model.entity.Article

@Dao
interface ArticleDao {
    @Query("SELECT * FROM articles")
    fun getAll(): Flow<List<Article>>

    @Query("SELECT * FROM articles WHERE LOWER(title) LIKE LOWER(:ttl)")
    fun getAllByTitle(ttl: String): Flow<List<Article>>

    @Query("SELECT * FROM articles WHERE id = :articleId")
    fun getById(articleId: Int): Flow<Article?>

    @Insert
    suspend fun insert(article: Article)

    @Update
    suspend fun update(article: Article)

    @Delete
    suspend fun delete(article: Article)
}