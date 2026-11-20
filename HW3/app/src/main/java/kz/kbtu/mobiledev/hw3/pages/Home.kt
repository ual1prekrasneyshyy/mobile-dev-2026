package kz.kbtu.mobiledev.hw3.pages

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun Home (navigate: (route: String) -> Unit) {
    Text (text = "Home page")
    Button(
        onClick = { navigate("add") }
    ) {
        Text(text = "Add article page")
    }
}