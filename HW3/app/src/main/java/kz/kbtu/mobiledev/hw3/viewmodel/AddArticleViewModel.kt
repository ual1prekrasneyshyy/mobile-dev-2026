package kz.kbtu.mobiledev.hw3.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kz.kbtu.mobiledev.hw3.model.dto.AddArticleDto
import kz.kbtu.mobiledev.hw3.model.dto.toArticle
import kz.kbtu.mobiledev.hw3.model.repository.ArticleRepository


class AddArticleViewModel(
    private val articleRepository: ArticleRepository
) : ViewModel() {
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    var uiState by mutableStateOf(AddArticleUiState())
        private set

    fun updateUiState(dto: AddArticleDto) {
        uiState =
            AddArticleUiState(dto = dto, isInputValid = validate(dto))
    }



    fun validate(dto: AddArticleDto = uiState.dto): Boolean {
        return with(dto) {
            title.isNotBlank() && content.isNotBlank() && author.isNotBlank()
        }
    }

    suspend fun save() {
        if(validate()) {
            articleRepository.insertArticle(uiState.dto.toArticle())
        }
    }
}

data class AddArticleUiState(
    val dto: AddArticleDto = AddArticleDto(),
    val isInputValid: Boolean = false
)