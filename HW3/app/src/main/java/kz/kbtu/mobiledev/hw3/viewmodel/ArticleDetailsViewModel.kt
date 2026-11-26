package kz.kbtu.mobiledev.hw3.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kz.kbtu.mobiledev.hw3.model.entity.Article
import kz.kbtu.mobiledev.hw3.model.repository.ArticleRepository

class ArticleDetailsViewModel(
    articleId: Int,
    val onReturnBack: () -> Unit,
    private val articleRepository: ArticleRepository,
) : ViewModel() {
    val articleDetailFlow = articleRepository.getArticleByIdStream(articleId).stateIn(scope = viewModelScope, started = SharingStarted.WhileSubscribed(5_000L), initialValue = Article(0, "", "", "", 0))

    init {
        viewModelScope.launch {
            articleRepository.updateArticle(
                Article(articleId, articleDetailFlow.value.title, articleDetailFlow.value.content, articleDetailFlow.value.author, articleDetailFlow.value.views + 1)
            )
        }
    }

    var showDeleteConfirmation by mutableStateOf(false);

    fun deleteArticle() {
        viewModelScope.launch {
            articleRepository.deleteArticle(articleDetailFlow.value)
            print("Removal")
        }
        onReturnBack()
    }
}