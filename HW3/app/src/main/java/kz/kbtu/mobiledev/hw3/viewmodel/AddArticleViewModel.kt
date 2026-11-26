package kz.kbtu.mobiledev.hw3.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kz.kbtu.mobiledev.hw3.model.entity.Article
import kz.kbtu.mobiledev.hw3.model.repository.ArticleRepository


class AddArticleViewModel(
    val onRedirect: () -> Unit,
    private val articleRepository: ArticleRepository
) : ViewModel() {
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    var articleTitle by mutableStateOf("")
    var articleContent by mutableStateOf("")
    var articleAuthor by mutableStateOf("")

    fun validate(): Boolean {
        if(articleTitle == "") {
            return false
        }

        if(articleContent == "") {
            return false
        }

        if(articleAuthor == "") {
            return false
        }

        return true
    }

    fun save() {
        if(validate()) {
            coroutineScope.launch(Dispatchers.IO) {
                articleRepository.insertArticle(Article(null, articleTitle, articleContent, articleAuthor, 0))
                clear()
            }
            onRedirect()
        }
    }

    fun clear() {
        articleTitle = ""
        articleContent = ""
        articleAuthor = ""
    }
}