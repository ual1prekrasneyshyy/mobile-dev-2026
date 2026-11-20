package kz.kbtu.mobiledev.hw3.core

data class Article (
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

val news = mutableListOf(
    Article(1, "Midterm for the AI courses completed", "Students has been defended their SIS project as a midterm task of the AI course", "Uali", 2),
    Article(2, "Astana Hub events has been performed", "A speech of QA Engineer has been performed in Almaty hub department of Astana Hub on Tuesday", "Aigerim", 3),
    Article(3, "Homework for Mobile Development course has been completed", "The second homework for Mobile Development course where I had to implement an Android Application with Jetpack Compose has been completed", "Adina", 5),
    Article(4, "Midterm Project for AI course has not been defended", "Yesterday his team didn't defend Midterm Project for the Artificial Intelligence courses due to shortage of time. The defense has been postopened to the next week", "Almas", 1),
    Article(5, "Midterm Project for Smart Systems course has been defeneded", "My team has successfully defended our midterm project for the Smart Systems courses on Monday", "Aruzhan", 7),
    Article(6, "Homework for Web Development courses has been defended", "I have successfully defended my second homework for the Web Development courses last Saturday", "Andrey", 4),
    Article(7, "Notification sending algorithm has been fixed", "The notification sending algorithm has been successfully fixed on my web project per employment", "Timur", 3),
)

fun addArticle(dto: ArticleDto) {
    news.add(
        Article(
            id = news.size + 1,
            title = dto.title,
            content = dto.content,
            author = dto.author,
            views = 1,
        )
    )
}