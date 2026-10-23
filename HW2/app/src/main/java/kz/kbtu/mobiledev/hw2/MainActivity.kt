package kz.kbtu.mobiledev.hw2

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kz.kbtu.mobiledev.hw2.core.db.addArticle
import kz.kbtu.mobiledev.hw2.core.db.news
import kz.kbtu.mobiledev.hw2.core.model.ArticleDto
import kz.kbtu.mobiledev.hw2.core.model.News
import kz.kbtu.mobiledev.hw2.ui.theme.HW2Theme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HW2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PageContent(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

class ArticleFormViewModel : ViewModel() {
    private val _dto = MutableStateFlow<ArticleDto>(ArticleDto())
    val dto: StateFlow<ArticleDto> = _dto.asStateFlow()

    fun onTitleFill(ttl: String) {
        _dto.update { ArticleDto(title = ttl, content = _dto.value.content, author = _dto.value.author) }
    }

    fun onContentFill(cnt: String) {
        _dto.update { ArticleDto(title = _dto.value.title, content = cnt, author = _dto.value.author) }
    }

    fun onAuthorFill(author: String) {
        _dto.update { ArticleDto(title = _dto.value.title, _dto.value.content, author = author) }
    }

    fun onSave(){
        addArticle(_dto.value)
        _dto.value = ArticleDto()
    }
}

@Composable
fun ArticleForm(
    news: MutableList<News>
) {
    var ttl by rememberSaveable { mutableStateOf("") }
    var cnt by rememberSaveable { mutableStateOf("") }
    var author by rememberSaveable { mutableStateOf("") }

    fun add(){
        news.add(News(id = news.size+1, title = ttl, content = cnt, author = author, views = 1))
        ttl = ""
        cnt = ""
        author = ""
    }

     Column (
        modifier = Modifier.fillMaxWidth()
     ) {
         Text(text = "Add Article", fontSize = 25.sp, fontWeight = FontWeight.Medium)
         Spacer(modifier = Modifier.height(8.dp))
         OutlinedTextField(
             value = ttl,
             onValueChange = {ttl = it},
             label = { Text(text = "Title:") },
             modifier = Modifier.fillMaxWidth()
         )
         Spacer(modifier = Modifier.height(3.dp))
         OutlinedTextField(
             value = cnt,
             onValueChange = {cnt = it},
             label = { Text(text = "Content:") },
             modifier = Modifier.fillMaxWidth()
         )
         Spacer(modifier = Modifier.height(3.dp))
         OutlinedTextField(
             value = author,
             onValueChange = {author = it},
             label = { Text(text = "Author:") },
             modifier = Modifier.fillMaxWidth()
         )
         Spacer(modifier = Modifier.height(3.dp))
         Button(
             onClick = { add() },
             content = { Text(text = "Add") },
             modifier = Modifier.padding(10.dp).fillMaxWidth()
         )
    }
}


@Composable
fun PageContent(modifier: Modifier) {
    Box(
        modifier = modifier
    ) {
        var searchQuery by rememberSaveable { mutableStateOf("") }
        val newsList by remember { mutableStateOf(news) }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(text = "News Portal", fontSize = 30.sp, fontWeight = FontWeight.Medium)
            Spacer(modifier = Modifier.height(12.dp))

            ArticleForm(news = newsList)


            Spacer(modifier = Modifier.height(20.dp))


            Text(text = "Articles", fontSize = 25.sp, fontWeight = FontWeight.Medium)
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = searchQuery,
                onValueChange = {searchQuery = it},
                label = {Text(text = "Find article")},
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            NewsContainer(news = newsList, searchQuery = searchQuery)
        }
    }
}

@Composable
fun NewsContainer(
    news: List<News>,
    searchQuery: String
) {
    val scrollState = rememberScrollState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .verticalScroll(scrollState)
    ) {
        news
            .filter { it.title.lowercase().contains(searchQuery.lowercase()) }
            .forEach { n ->
                Row { ArticleBox(n) }
                Spacer(modifier = Modifier.height(9.dp))
            }
    }
}

@Composable
fun ArticleBox(n: News) {
    Card(

    )  {
        Column (
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
        ) {
            Row {
                Text(
                    text = n.title,
                    fontSize = 18   .sp,
                    fontWeight = FontWeight.Medium
                )
            }
            Spacer(modifier = Modifier.height(7.dp))
            Row {
                Text(
                    text = n.content,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column (
                    horizontalAlignment = AbsoluteAlignment.Left
                ) {
                    Text(
                        text = "Posted by ${n.author}",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
                Column (
                    horizontalAlignment = AbsoluteAlignment.Right
                ) {
                    Text(
                        text = "${n.views} views",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
            }
        }
    }
}
