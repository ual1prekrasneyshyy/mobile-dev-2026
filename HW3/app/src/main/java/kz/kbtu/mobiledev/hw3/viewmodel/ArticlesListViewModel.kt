package kz.kbtu.mobiledev.hw3.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kz.kbtu.mobiledev.hw3.model.entity.Article
import kz.kbtu.mobiledev.hw3.model.repository.ArticleRepository
import kotlin.collections.listOf

class ArticlesListViewModel(private val articleRepository: ArticleRepository) : ViewModel() {
    var searchQuery by mutableStateOf("")

    val articlesFlow = articleRepository.getAllArticlesByTitleStream(searchQuery).stateIn(scope = viewModelScope, started = SharingStarted.WhileSubscribed(5_000L), initialValue = listOf())


}