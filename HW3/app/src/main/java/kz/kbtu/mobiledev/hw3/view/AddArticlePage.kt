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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kz.kbtu.mobiledev.hw3.ui.theme.Purple80
import kz.kbtu.mobiledev.hw3.ui.theme.PurpleGrey40
import kz.kbtu.mobiledev.hw3.viewmodel.AddArticleViewModel
import kz.kbtu.mobiledev.hw3.viewmodel.UpdateArticleViewModel

@Composable
fun AddArticlePage(vm: AddArticleViewModel) {
    Column (
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Add Article", fontSize = 25.sp, fontWeight = FontWeight.Medium)
        Spacer(modifier = Modifier.height(8.dp))
        ArticleForm(
            articleTitle = vm.articleTitle,
            onChangeArticleTitle = { vm.articleTitle = it },
            articleContent = vm.articleContent,
            onChangeArticleContent = { vm.articleContent = it },
            articleAuthor = vm.articleAuthor,
            onChangeArticleAuthor = { vm.articleAuthor = it },
            onFormSubmit = { vm.save() },
            onReturnBack = { vm.onRedirect() }
        )
    }
}

@Composable
fun UpdateArticlePage(vm: UpdateArticleViewModel) {
    Column (
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Update Article", fontSize = 25.sp, fontWeight = FontWeight.Medium)
        Spacer(modifier = Modifier.height(8.dp))
        ArticleForm(
            articleTitle = vm.articleTitle,
            onChangeArticleTitle = { vm.articleTitle = it },
            articleContent = vm.articleContent,
            onChangeArticleContent = { vm.articleContent = it },
            articleAuthor = vm.articleAuthor,
            onChangeArticleAuthor = { vm.articleAuthor = it },
            onFormSubmit = { vm.save() },
            onReturnBack = { vm.onRedirect() }
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
    onReturnBack: () -> Unit
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
            modifier = Modifier.padding(10.dp).fillMaxWidth()
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