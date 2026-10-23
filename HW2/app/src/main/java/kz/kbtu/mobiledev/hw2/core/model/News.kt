package kz.kbtu.mobiledev.hw2.core.model

data class News(
    val id: Int,
    val title: String,
    val content: String,
    val author: String,
    val views: Int,
)

data class ArticleDto(
    var title: String = "",
    var content: String = "",
    var author: String = ""
)