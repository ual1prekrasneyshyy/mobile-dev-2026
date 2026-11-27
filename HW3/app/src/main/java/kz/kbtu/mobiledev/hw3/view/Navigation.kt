package kz.kbtu.mobiledev.hw3.view

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Navigation(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = "home", modifier = Modifier.padding(12.dp)) {
        composable(route = "home") {
            ArticleListPage(
                onNavigateToAddArticlePage = { navController.navigate("add") },
                onNavigateToArticleDetailsPage = { navController.navigate("view/${it}") },
            )
        }
        composable(route = "add") {
            AddArticlePage(
                onRedirect = { navController.navigateUp() },
                onNavigateBack = { navController.popBackStack() }
            )
        }
        composable(
            route = "view/{id}",
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
            })
        ) {
            ArticleDetailsPage(
                onNavigateToUpdateArticlePage = { navController.navigate("edit/${it}") },
                onReturnBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
        composable(
            route = "edit/{id}",
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
            })
        ) {
            UpdateArticlePage(
                onRedirect = { navController.navigateUp() },
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}