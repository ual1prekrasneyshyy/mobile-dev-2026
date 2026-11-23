package kz.kbtu.mobiledev.hw3.model.repository

import kotlinx.coroutines.flow.Flow
import kz.kbtu.mobiledev.hw3.model.dao.ArticleDao
import kz.kbtu.mobiledev.hw3.model.entity.Article

class ArticleRepositoryImpl(private val articleDao: ArticleDao): ArticleRepository {
    override fun getAllArticlesStream(): Flow<List<Article>> = articleDao.getAll()

    override fun getAllArticlesByTitleStream(title: String): Flow<List<Article>> = articleDao.getAllByTitle("%$title%")

    override fun getArticleByIdStream(id: Int): Flow<Article> = articleDao.getById(id)

    override suspend fun insertArticle(article: Article) = articleDao.insert(article)

    override suspend fun updateArticle(article: Article) = articleDao.update(article)

    override suspend fun deleteArticle(article: Article) = articleDao.delete(article)
}