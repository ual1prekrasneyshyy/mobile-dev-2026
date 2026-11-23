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


class AddArticleViewModel(private val articleRepository: ArticleRepository) : ViewModel() {
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    private var _articleTitle by mutableStateOf("")
    private var _articleContent by mutableStateOf("")
    private var _articleAuthor by mutableStateOf("")

    val articleTitle = _articleTitle
    val articleContent = _articleContent
    val articleAuthor = _articleAuthor

    fun setArticleTitle (ttl: String) {
        _articleTitle = ttl
    }

    fun setArticleContent (cnt: String) {
        _articleContent = cnt
    }

    fun setArticleAuthor (author: String) {
        _articleAuthor = author
    }

    fun validate(): Boolean {
        if(_articleTitle == "") {
            return false
        }

        if(_articleContent == "") {
            return false
        }

        if(_articleAuthor == "") {
            return false
        }

        return true
    }

    fun save() {
        if(validate()) {
            coroutineScope.launch(Dispatchers.IO) {
                articleRepository.insertArticle(Article(null, _articleTitle, _articleContent, _articleAuthor, 0))
            }
//                navigateBack()
        }
    }
}