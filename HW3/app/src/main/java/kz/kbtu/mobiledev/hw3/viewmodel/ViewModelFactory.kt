package kz.kbtu.mobiledev.hw3.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kz.kbtu.mobiledev.hw3.model.repository.ArticleRepository

object ViewModelFactory {
    val Factory: ViewModelProvider.Factory = viewModelFactory {
        val articleRepositoryContainer = object : CreationExtras.Key<ArticleRepository> {}

        initializer {
            val articleRepository =  this[articleRepositoryContainer] as ArticleRepository
            ArticlesListViewModel(articleRepository)
        }
    }
}