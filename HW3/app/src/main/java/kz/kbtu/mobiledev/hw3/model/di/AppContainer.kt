package kz.kbtu.mobiledev.hw3.model.di

import android.content.Context
import kz.kbtu.mobiledev.hw3.model.db.AppDatabase
import kz.kbtu.mobiledev.hw3.model.repository.ArticleRepository
import kz.kbtu.mobiledev.hw3.model.repository.ArticleRepositoryImpl

interface AppContainer {
    val articleRepository: ArticleRepository
}

class AppContainerImpl(private val context: Context) : AppContainer {
    override val articleRepository: ArticleRepository by lazy {
        ArticleRepositoryImpl(AppDatabase.getDatabase(context).articleDao())
    }
}