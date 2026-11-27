package kz.kbtu.mobiledev.hw3.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kz.kbtu.mobiledev.hw3.model.dto.ArticleDetails
import kz.kbtu.mobiledev.hw3.model.entity.Article
import kz.kbtu.mobiledev.hw3.model.repository.ArticleRepository
import kotlin.collections.listOf

class ArticlesListViewModel(private val articleRepository: ArticleRepository) : ViewModel() {
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    fun updateSearchQuery(value: String) {
        _searchQuery.value = value
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val uiState: StateFlow<ArticleListUiState> =
        _searchQuery
            .flatMapLatest { query ->
                articleRepository.getAllArticlesByTitleStream(query)
            }
            .map { ArticleListUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000L),
                initialValue = ArticleListUiState()
            )

    fun addView(article: Article) {
        viewModelScope.launch {
            articleRepository.updateArticle(
                Article(
                    article.id,
                    article.title,
                    article.content,
                    article.author,
                    article.views + 1
                )
            )
        }
    }
}

data class ArticleListUiState(
    val articleList: List<Article> = listOf()
)