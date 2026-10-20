package kz.kbtu.mobiledev.hw2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kz.kbtu.mobiledev.hw2.core.db.news
import kz.kbtu.mobiledev.hw2.ui.theme.HW2Theme
import kz.kbtu.mobiledev.hw2.ui.theme.Purple40

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HW2Theme {
                PageContent()
            }
        }
    }
}

@Composable
fun PageContent() {
    Column {
        news.forEach { m ->
            Box(modifier = Modifier.padding(1.dp).border(1.dp, Purple40))  {
                Text(text = "${m.title} ${m.content} ${m.views} views Posted by ${m.author}")
            }
        }
    }
}
