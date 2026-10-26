package kz.kbtu.mobiledev.midterm.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kz.kbtu.mobiledev.midterm.viewModel.ItemsViewModel

@Composable
fun AddItemPage(viewModel: ItemsViewModel)
{
    Column(
        modifier = Modifier.padding(12.dp)
    ) {
        Text(
            text = "Add Item",
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(12.dp))
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(
                text = "Code:",
                fontSize = 16.sp,
                fontWeight = FontWeight.W300
            )
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.addItemCode,
                onValueChange = { viewModel.setCode(it) }
            )
            Spacer(modifier = Modifier.height(7.dp))
            Text(
                text = "Name:",
                fontSize = 16.sp,
                fontWeight = FontWeight.W300
            )
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.addItemName,
                onValueChange = { viewModel.setName(it) }
            )
            Spacer(modifier = Modifier.height(7.dp))
            Text(
                text = "Price:",
                fontSize = 16.sp,
                fontWeight = FontWeight.W300
            )
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.addItemPrice.toString(),
                onValueChange = { viewModel.setPrice(it.toInt()) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )
            Spacer(modifier = Modifier.height(7.dp))
            Text(
                text = "Count:",
                fontSize = 16.sp,
                fontWeight = FontWeight.W300
            )
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.addItemCount.toString(),
                onValueChange = { viewModel.setCount(it.toInt()) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )
            Spacer(modifier = Modifier.height(7.dp))
            Button(
                content = { Text(text = "ADD") },
                onClick = { viewModel.addItem() }
            )
        }
    }
}