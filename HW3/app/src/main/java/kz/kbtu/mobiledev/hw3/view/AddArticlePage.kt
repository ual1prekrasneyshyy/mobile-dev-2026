package kz.kbtu.mobiledev.hw3.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import kz.kbtu.mobiledev.hw3.ui.theme.Purple80
import kz.kbtu.mobiledev.hw3.ui.theme.PurpleGrey40
import kz.kbtu.mobiledev.hw3.viewmodel.AddArticleViewModel
import kz.kbtu.mobiledev.hw3.viewmodel.UpdateArticleViewModel
import kz.kbtu.mobiledev.hw3.viewmodel.ViewModelFactoryProvider

@Composable
fun AddArticlePage(
    onNavigateBack: () -> Unit,
    onRedirect: () -> Unit,
    vm: AddArticleViewModel = viewModel(factory = ViewModelFactoryProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val uiState = vm.uiState
    val dto = uiState.dto
    val onValueChange = vm::updateUiState

    Column (
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Add Article", fontSize = 25.sp, fontWeight = FontWeight.Medium)
        Spacer(modifier = Modifier.height(8.dp))
        ArticleForm(
            articleTitle = dto.title,
            onChangeArticleTitle = { onValueChange(dto.copy(title = it)) },
            articleContent = dto.content,
            onChangeArticleContent = { onValueChange(dto.copy(content = it)) },
            articleAuthor = dto.author,
            onChangeArticleAuthor = { onValueChange(dto.copy(author = it)) },
            onFormSubmit = {
                coroutineScope.launch {
                    vm.save()
                    onRedirect()
                }
            },
            onReturnBack = { onNavigateBack() },
            submitBtnEnabled = uiState.isInputValid
        )
    }
}

@Composable
fun UpdateArticlePage(
    onNavigateBack: () -> Unit,
    onRedirect: () -> Unit,
    vm: UpdateArticleViewModel = viewModel(factory = ViewModelFactoryProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val uiState = vm.uiState
    val articleDetails = uiState.articleDetails
    val onValueChange = vm::updateUiState

    Column (
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Update Article", fontSize = 25.sp, fontWeight = FontWeight.Medium)
        Spacer(modifier = Modifier.height(8.dp))
        ArticleForm(
            articleTitle = articleDetails.title,
            onChangeArticleTitle = { onValueChange(articleDetails.copy(title = it)) },
            articleContent = articleDetails.content,
            onChangeArticleContent = { onValueChange(articleDetails.copy(content = it)) },
            articleAuthor = articleDetails.author,
            onChangeArticleAuthor = { onValueChange(articleDetails.copy(author = it)) },
            onFormSubmit = {
                coroutineScope.launch {
                    vm.save()
                    onRedirect()
                }
            },
            onReturnBack = { onNavigateBack() },
            submitBtnEnabled = uiState.isInputValid
        )
    }
}

@Composable
fun ArticleForm(
    articleTitle: String,
    onChangeArticleTitle: (ttl: String) -> Unit,
    articleContent: String,
    onChangeArticleContent: (ttl: String) -> Unit,
    articleAuthor: String,
    onChangeArticleAuthor: (ttl: String) -> Unit,
    onFormSubmit: () -> Unit,
    onReturnBack: () -> Unit,
    submitBtnEnabled: Boolean,
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = articleTitle,
            onValueChange = { onChangeArticleTitle(it) },
            label = { Text(text = "Title:") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(3.dp))
        OutlinedTextField(
            value = articleContent,
            onValueChange = { onChangeArticleContent(it) },
            label = { Text(text = "Content:") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(3.dp))
        OutlinedTextField(
            value = articleAuthor,
            onValueChange = { onChangeArticleAuthor(it) },
            label = { Text(text = "Author:") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(3.dp))
        Button(
            onClick = { onFormSubmit() },
            content = { Text(text = "Save") },
            modifier = Modifier.padding(10.dp).fillMaxWidth(),
            enabled = submitBtnEnabled
        )
        Spacer(modifier = Modifier.height(3.dp))
        Button(
            onClick = { onReturnBack() },
            content = { Text(text = "Cancel") },
            modifier = Modifier.padding(10.dp).fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Purple80,
                contentColor = PurpleGrey40
            )
        )
    }
}