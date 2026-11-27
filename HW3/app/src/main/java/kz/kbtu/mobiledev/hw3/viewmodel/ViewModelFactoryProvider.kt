package kz.kbtu.mobiledev.hw3.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kz.kbtu.mobiledev.hw3.NewsApplication

object ViewModelFactoryProvider {
    val Factory = viewModelFactory {
        initializer {
            ArticlesListViewModel(newsApplication().articleRepository)
        }
        initializer {
            AddArticleViewModel(newsApplication().articleRepository)
        }
        initializer {
            ArticleDetailsViewModel(this.createSavedStateHandle(), newsApplication().articleRepository)
        }
        initializer {
            UpdateArticleViewModel(this.createSavedStateHandle(), newsApplication().articleRepository)
        }
    }
}

fun CreationExtras.newsApplication(): NewsApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]) as NewsApplication