package kz.kbtu.mobiledev.hw3.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kz.kbtu.mobiledev.hw3.model.repository.ArticleRepository
import kotlin.collections.listOf

class ArticlesListViewModel(private val articleRepository: ArticleRepository) : ViewModel() {
    private val _articlesFlow = articleRepository.getAllArticlesStream().stateIn(scope = viewModelScope, started = SharingStarted.WhileSubscribed(5_000L), initialValue = listOf())
    val articlesFlow = _articlesFlow
}