package kz.kbtu.mobiledev.midterm.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kz.kbtu.mobiledev.midterm.viewModel.ItemsViewModel

@Composable
fun CartPage(viewModel: ItemsViewModel){
    val scrollState = rememberScrollState()

    val items by viewModel.items.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.padding(12.dp)
    ) {
        Text(
            text = "Cart",
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Total sum: ${items.filter { it.numberInCart > 0 }.map { it.price * it.numberInCart }.sum() } KZT",
            fontSize = 18.sp,
            fontWeight = FontWeight.W500
        )
        Spacer(modifier = Modifier.height(10.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items.filter { it.numberInCart > 0 }.forEach { ItemList(it, false, viewModel) }
        }
    }
}