package kz.kbtu.mobiledev.hw3.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kz.kbtu.mobiledev.hw3.ui.theme.Pink40
import kz.kbtu.mobiledev.hw3.ui.theme.Purple40
import kz.kbtu.mobiledev.hw3.ui.theme.PurpleGrey40
import kz.kbtu.mobiledev.hw3.viewmodel.ArticleDetailsViewModel

@Composable
fun ArticleDetailsPage(
    onNavigateToUpdateArticlePage: (id: Int) -> Unit,
    vm: ArticleDetailsViewModel
) {
    val articleDetail by vm.articleDetailFlow.collectAsStateWithLifecycle()

    Column {
        Text(
            text = "Article #${articleDetail.id}",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(5.dp)
        )
        Text(
            text = articleDetail.title,
            fontSize = 30.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(5.dp)
        )
        Text(
            text = articleDetail.content,
            fontSize = 24.sp,
            fontWeight = FontWeight.W500,
            modifier = Modifier.padding(5.dp)
        )
        Text(
            text = "Posted by ${articleDetail.author}",
            fontSize = 20.sp,
            fontWeight = FontWeight.W300,
            modifier = Modifier.padding(5.dp)
        )
        Text(
            text = "${articleDetail.views} views",
            fontSize = 18.sp,
            fontWeight = FontWeight.W300,
            modifier = Modifier.padding(5.dp)
        )

        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth().padding(10.dp)
        ) {
            Column (horizontalAlignment = Alignment.Start)  {
                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = Purple40),
                    onClick = { vm.onReturnBack() }
                ) {
                    Text(text = "Home")
                }
            }
            Column (horizontalAlignment = Alignment.CenterHorizontally)  {
                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = PurpleGrey40),
                    onClick = { onNavigateToUpdateArticlePage(articleDetail.id!!) }
                ) {
                    Text(text = "Edit")
                }
            }
            Column (horizontalAlignment = Alignment.End) {
                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = Pink40),
                    onClick = { vm.showDeleteConfirmation = true }
                ) {
                    Text(text = "Delete")
                }
            }
        }

        if(vm.showDeleteConfirmation) {
            Card {
                Text(
                    text = "Are you sure? Delete?",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W500,
                    modifier = Modifier.padding(10.dp),
                    color = Pink40
                )
                Row (
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth().padding(10.dp)
                ) {
                    Column (horizontalAlignment = Alignment.Start)  {
                        Button(
                            colors = ButtonDefaults.buttonColors(containerColor = Purple40),
                            onClick = { vm.showDeleteConfirmation = false }
                        ) {
                            Text(text = "No")
                        }
                    }
                    Column (horizontalAlignment = Alignment.End) {
                        Button(
                            colors = ButtonDefaults.buttonColors(containerColor = Pink40),
                            onClick = { vm.deleteArticle() }
                        ) {
                            Text(text = "Yes")
                        }
                    }
                }
            }
        }
    }
}