package kz.kbtu.mobiledev.hw3.view

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import kz.kbtu.mobiledev.hw3.model.repository.ArticleRepository
import kz.kbtu.mobiledev.hw3.viewmodel.AddArticleViewModel
import kz.kbtu.mobiledev.hw3.viewmodel.ArticleDetailsViewModel
import kz.kbtu.mobiledev.hw3.viewmodel.ArticlesListViewModel
import kz.kbtu.mobiledev.hw3.viewmodel.UpdateArticleViewModel

@Serializable
object ArticleList

@Serializable
object AddArticle

@Serializable
data class ArticleDetails(val id: Int)

@Serializable
data class UpdateArticle(val id: Int)

@Composable
fun Navigation(articleRepository: ArticleRepository) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ArticleList, modifier = Modifier.padding(12.dp)) {
        composable<ArticleList> {
            ArticleListPage(
                onNavigateToAddArticlePage = { navController.navigate(AddArticle) },
                onNavigateToArticleDetailsPage = { navController.navigate(ArticleDetails(it)) },
                vm = ArticlesListViewModel(articleRepository)
            )
        }
        composable<AddArticle> {
            AddArticlePage(
                vm = AddArticleViewModel(
                    articleRepository = articleRepository,
                    onRedirect = { navController.navigate(ArticleList) }
                )
            )
        }
        composable<ArticleDetails> { backStackEntry ->
            val articleDetails: ArticleDetails = backStackEntry.toRoute()
            ArticleDetailsPage(
                onNavigateToUpdateArticlePage = { navController.navigate(UpdateArticle(it)) },
                vm = ArticleDetailsViewModel(
                    articleId = articleDetails.id,
                    articleRepository = articleRepository,
                    onReturnBack = { navController.navigate(ArticleList) }
                )
            )
        }
        composable<UpdateArticle> {  backStackEntry ->
            val updateArticle: UpdateArticle = backStackEntry.toRoute()
            UpdateArticlePage(
                vm = UpdateArticleViewModel(
                    articleId = updateArticle.id,
                    articleRepository = articleRepository,
                    onRedirect = { navController.navigate(ArticleDetails(updateArticle.id)) }
                )
            )
        }
    }
}