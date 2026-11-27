package kz.kbtu.mobiledev.hw3.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import kz.kbtu.mobiledev.hw3.ui.theme.Pink40
import kz.kbtu.mobiledev.hw3.ui.theme.Purple40
import kz.kbtu.mobiledev.hw3.ui.theme.PurpleGrey40
import kz.kbtu.mobiledev.hw3.viewmodel.ArticleDetailsViewModel
import kz.kbtu.mobiledev.hw3.viewmodel.ViewModelFactoryProvider

@Composable
fun ArticleDetailsPage(
    onNavigateToUpdateArticlePage: (id: Int) -> Unit,
    onReturnBack: () -> Unit,
    onNavigateUp: () -> Unit,
    vm: ArticleDetailsViewModel = viewModel(factory = ViewModelFactoryProvider.Factory),
) {
    val uiState by vm.uiState.collectAsStateWithLifecycle()
    val articleDetail = uiState.articleDetails

    var showDeleteConfirmation by rememberSaveable { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

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
                    onClick = {
                        onReturnBack()
                    }
                ) {
                    Text(text = "Home")
                }
            }
            Column (horizontalAlignment = Alignment.CenterHorizontally)  {
                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = PurpleGrey40),
                    onClick = { onNavigateToUpdateArticlePage(articleDetail.id) }
                ) {
                    Text(text = "Edit")
                }
            }
            Column (horizontalAlignment = Alignment.End) {
                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = Pink40),
                    onClick = { showDeleteConfirmation = true }
                ) {
                    Text(text = "Delete")
                }
            }
        }

        if(showDeleteConfirmation) {
            DeleteConfirmationDialog(
                onDeleteCancel = { showDeleteConfirmation = false },
                onDeleteConfirm = {
                    showDeleteConfirmation = false
                    coroutineScope.launch {
                        vm.deleteArticle()
                        onNavigateUp()
                    }
                }
            )
        }
    }
}

@Composable
private fun DeleteConfirmationDialog(
    onDeleteConfirm: () -> Unit,
    onDeleteCancel: () -> Unit
) {
    AlertDialog(onDismissRequest = { onDeleteCancel },
        title = { Text(text = "Are sure?") },
        text = { Text(text = "Article will be permanently deleted!") },
        dismissButton = {
            TextButton(onClick = onDeleteCancel) {
                Text(text = "No")
            }
        },
        confirmButton = {
            TextButton(onClick = onDeleteConfirm) {
                Text(text = "Yes")
            }
        }
    )
}