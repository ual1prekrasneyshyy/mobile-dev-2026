package kz.kbtu.mobiledev.hw3.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.coroutineScope
import kz.kbtu.mobiledev.hw3.viewmodel.AddArticleViewModel

@Composable
fun AddArticlePage(vm: AddArticleViewModel){
    val onClick = {

    }

    Text(text = "Add article page")

    Column (
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Add Article", fontSize = 25.sp, fontWeight = FontWeight.Medium)
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = vm.articleTitle,
            onValueChange = { vm.setArticleTitle(it) },
            label = { Text(text = "Title:") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(3.dp))
        OutlinedTextField(
            value = vm.articleContent,
            onValueChange = { vm.setArticleContent(it) },
            label = { Text(text = "Content:") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(3.dp))
        OutlinedTextField(
            value = vm.articleAuthor,
            onValueChange = { vm.setArticleAuthor(it) },
            label = { Text(text = "Author:") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(3.dp))
        Button(
            onClick = { vm.save() },
            content = { Text(text = "Add") },
            modifier = Modifier.padding(10.dp).fillMaxWidth()
        )
    }
}