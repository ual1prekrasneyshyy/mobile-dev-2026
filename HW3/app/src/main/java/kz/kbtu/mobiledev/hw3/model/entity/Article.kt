package kz.kbtu.mobiledev.hw3.model.entity

import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity(tableName = "articles")
data class Article (
    @PrimaryKey val id: Int? = null,
    val title: String = "",
    val content: String = "",
    val author: String = "",
    val views: Int = 0,
)