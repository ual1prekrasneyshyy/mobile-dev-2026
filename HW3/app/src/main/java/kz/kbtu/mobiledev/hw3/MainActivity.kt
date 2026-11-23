package kz.kbtu.mobiledev.hw3

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.activity.viewModels
import kz.kbtu.mobiledev.hw3.model.di.AppContainerImpl
import kz.kbtu.mobiledev.hw3.model.repository.ArticleRepository
import kz.kbtu.mobiledev.hw3.view.AddArticlePage
import kz.kbtu.mobiledev.hw3.view.HomePage
import kz.kbtu.mobiledev.hw3.ui.theme.HW3Theme
import kz.kbtu.mobiledev.hw3.viewmodel.AddArticleViewModel
import kz.kbtu.mobiledev.hw3.viewmodel.ArticlesListViewModel


class MainActivity : ComponentActivity() {
    lateinit var articleRepository: ArticleRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val container = AppContainerImpl(this)
        articleRepository = container.articleRepository

        val articlesListViewModel = ArticlesListViewModel(articleRepository)

        val addArticleViewModel = AddArticleViewModel(articleRepository)

        enableEdgeToEdge()
        setContent {
            HW3Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()

                    // think
                    val navigate = {route: String -> navController.navigate(route)}


                    NavHost(navController = navController, startDestination = "home", modifier = Modifier.padding(innerPadding)) {
                        composable("home") {
                            HomePage(navigate, articlesListViewModel)
                        }
                        composable("add") {
                            AddArticlePage(addArticleViewModel)
                        }
                    }
                }
            }
        }
    }
}