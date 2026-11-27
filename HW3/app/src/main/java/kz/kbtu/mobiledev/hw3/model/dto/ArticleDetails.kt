package kz.kbtu.mobiledev.hw3.model.dto

import kz.kbtu.mobiledev.hw3.model.entity.Article

data class ArticleDetails(
    val id: Int = 0,
    val title: String = "",
    val content: String = "",
    val author: String = "",
    val views: Int = 0,
)

data class AddArticleDto(
    val title: String = "",
    val content: String = "",
    val author: String = "",
)

fun ArticleDetails.toArticle(): Article = Article(id, title, content, author, views)

fun AddArticleDto.toArticle(): Article = Article(null, title, content, author, 0)

fun Article.toArticleDetails(): ArticleDetails = ArticleDetails(id ?: -1, title, content, author, views)

