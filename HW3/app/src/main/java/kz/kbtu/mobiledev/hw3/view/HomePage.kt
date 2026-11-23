package kz.kbtu.mobiledev.hw3.view

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kz.kbtu.mobiledev.hw3.viewmodel.ArticlesListViewModel

@Composable
fun HomePage (
    navigate: (route: String) -> Unit,
    viewModel: ArticlesListViewModel
) {
    Text (text = "Home page")

    val articles by viewModel.articlesFlow.collectAsStateWithLifecycle()

    articles.forEach {
        Text(text = it.title)
    }



    Button(
        onClick = { navigate("add") }
    ) {
        Text(text = "Add article page")
    }

}