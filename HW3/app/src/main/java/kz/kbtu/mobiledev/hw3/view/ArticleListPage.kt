package kz.kbtu.mobiledev.hw3.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kz.kbtu.mobiledev.hw3.ui.theme.Purple40
import kz.kbtu.mobiledev.hw3.ui.theme.PurpleGrey40
import kz.kbtu.mobiledev.hw3.viewmodel.ArticlesListViewModel

@Composable
fun ArticleListPage (
    onNavigateToAddArticlePage: () -> Unit,
    onNavigateToArticleDetailsPage: (id: Int) -> Unit,
    vm: ArticlesListViewModel
) {
    val articles by vm.articlesFlow.collectAsStateWithLifecycle()

    Column {
        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth().padding(10.dp)
        ) {
            Column (horizontalAlignment = Alignment.Start) {
                Text(text = "Articles (${articles.size})", fontSize = 40.sp, fontWeight = FontWeight.Medium)
            }
            Column (horizontalAlignment = Alignment.End) {
                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = PurpleGrey40),
                    onClick = { onNavigateToAddArticlePage() }
                ) {
                    Text(text = "Add article page")
                }
            }
        }

        Spacer(modifier = Modifier.height(5.dp))

        OutlinedTextField(
            value = vm.searchQuery,
            onValueChange = { vm.searchQuery = it },
            label = {Text(text = "Find article")},
            modifier = Modifier.fillMaxWidth().padding(5.dp)
        )

        LazyColumn(
            modifier = Modifier.padding(vertical = 4.dp)
        ) {
            items(items = articles) {
                Card(modifier = Modifier.fillMaxWidth().padding(5.dp)) {
                    Text(
                        modifier = Modifier.padding(10.dp),
                        text = "Article #${it.id}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    Text(
                        modifier = Modifier.padding(10.dp),
                        text = it.title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )

                    Text(
                        modifier = Modifier.padding(10.dp),
                        text = "Posted by ${it.author}",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W300
                    )

                    Button(
                        modifier = Modifier.padding(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Purple40),
                        onClick = { onNavigateToArticleDetailsPage(it.id!!) }
                    ) {
                        Text(text = "Details")
                    }
                }
            }
        }
    }
}