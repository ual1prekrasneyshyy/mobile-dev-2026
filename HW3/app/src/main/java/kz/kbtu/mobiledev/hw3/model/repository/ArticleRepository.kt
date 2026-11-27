package kz.kbtu.mobiledev.hw3.model.repository

import kotlinx.coroutines.flow.Flow
import kz.kbtu.mobiledev.hw3.model.entity.Article

interface ArticleRepository {
    fun getAllArticlesStream(): Flow<List<Article>>
    fun getAllArticlesByTitleStream(title: String): Flow<List<Article>>
    fun getArticleByIdStream(id: Int): Flow<Article?>
    suspend fun insertArticle(article: Article)
    suspend fun updateArticle(article: Article)
    suspend fun deleteArticle(article: Article)
}