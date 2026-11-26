package kz.kbtu.mobiledev.hw3.viewmodel

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kz.kbtu.mobiledev.hw3.model.entity.Article
import kz.kbtu.mobiledev.hw3.model.repository.ArticleRepository


class UpdateArticleViewModel(
    private val articleId: Int,
    private val articleRepository: ArticleRepository,
    val onRedirect: () -> Unit
) : ViewModel() {
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    var articleTitle by mutableStateOf("")
    var articleContent by mutableStateOf("")
    var articleAuthor by mutableStateOf("")

    // tmp
    var articleViews by mutableIntStateOf(0)

    init {
        viewModelScope.launch {
            val article = articleRepository.getArticleByIdStream(articleId).first()
            articleTitle = article.title
            articleContent = article.content
            articleAuthor = article.author

            /// tmp
            articleViews = article.views
        }
    }


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
                articleRepository.updateArticle(Article(articleId, articleTitle, articleContent, articleAuthor, articleViews))
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