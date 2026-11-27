package kz.kbtu.mobiledev.hw3

import android.app.Application
import kz.kbtu.mobiledev.hw3.model.di.AppContainerImpl
import kz.kbtu.mobiledev.hw3.model.repository.ArticleRepository

class NewsApplication: Application() {
    lateinit var articleRepository: ArticleRepository

    override fun onCreate() {
        super.onCreate()
        val container = AppContainerImpl(this)
        articleRepository = container.articleRepository
    }
}