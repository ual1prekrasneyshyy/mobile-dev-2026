package kz.kbtu.mobiledev.hw3.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kz.kbtu.mobiledev.hw3.model.dto.ArticleDetails
import kz.kbtu.mobiledev.hw3.model.dto.toArticle
import kz.kbtu.mobiledev.hw3.model.dto.toArticleDetails
import kz.kbtu.mobiledev.hw3.model.entity.Article
import kz.kbtu.mobiledev.hw3.model.repository.ArticleRepository


class UpdateArticleViewModel(
    savedStateHandle: SavedStateHandle,
    private val articleRepository: ArticleRepository,
) : ViewModel() {
    private val articleId: Int = checkNotNull(savedStateHandle["id"])

    var uiState by mutableStateOf(UpdateArticleUiState())
        private set

    fun updateUiState(articleDetails: ArticleDetails) {
        uiState =
            UpdateArticleUiState(articleDetails = articleDetails, isInputValid = validate(articleDetails))
    }

    init {
        viewModelScope.launch {
            uiState = articleRepository.getArticleByIdStream(articleId).filterNotNull().first().toState()
        }
    }

    fun validate(articleDetails: ArticleDetails = uiState.articleDetails): Boolean {
        return with(articleDetails) {
            title.isNotBlank() && content.isNotBlank() && author.isNotBlank()
        }
    }

    suspend fun save() {
        if(validate()) {
            articleRepository.updateArticle(uiState.articleDetails.toArticle())
        }
    }
}

data class UpdateArticleUiState(
    val articleDetails: ArticleDetails = ArticleDetails(),
    val isInputValid: Boolean = true
)

fun Article.toState() = UpdateArticleUiState(toArticleDetails())