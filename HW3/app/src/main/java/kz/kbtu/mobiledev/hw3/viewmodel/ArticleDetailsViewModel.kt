package kz.kbtu.mobiledev.hw3.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kz.kbtu.mobiledev.hw3.model.dto.ArticleDetails
import kz.kbtu.mobiledev.hw3.model.dto.toArticle
import kz.kbtu.mobiledev.hw3.model.dto.toArticleDetails
import kz.kbtu.mobiledev.hw3.model.repository.ArticleRepository

class ArticleDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val articleRepository: ArticleRepository,
) : ViewModel() {
    private val articleId: Int = checkNotNull(savedStateHandle["id"])

    val uiState: StateFlow<ArticleDetailsUiState> =
        articleRepository.getArticleByIdStream(articleId)
            .filterNotNull()
            .map { ArticleDetailsUiState(articleDetails = it.toArticleDetails()) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000L),
                initialValue = ArticleDetailsUiState()
            )


    suspend fun deleteArticle() {
        articleRepository.deleteArticle(uiState.value.articleDetails.toArticle())
    }
}

data class ArticleDetailsUiState(
    val articleDetails: ArticleDetails = ArticleDetails(),
)