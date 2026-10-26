package kz.kbtu.mobiledev.midterm.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kz.kbtu.mobiledev.midterm.core.Item
import kz.kbtu.mobiledev.midterm.viewModel.ItemsViewModel

@Composable
fun ItemList(
    it: Item,
    mainPage: Boolean,
    viewModel: ItemsViewModel
){
    Card (
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(15.dp)
        ) {
            Text(
                text = it.name,
                fontSize = 21.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(7.dp))
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    horizontalAlignment = AbsoluteAlignment.Left
                ) {
                    Text(
                        text = it.code,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W200
                    )
                }
                Column(
                    horizontalAlignment = AbsoluteAlignment.Right
                ) {
                    Text(
                        text = "${it.price} KZT",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W500
                    )
                }
            }
            Spacer(modifier = Modifier.height(7.dp))
            if(mainPage){
                Text(
                    text = "${it.numberOfAvailable} items on stock",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W300
                )
                Spacer(modifier = Modifier.height(7.dp))
                Button(
                    content = {Text(text = "Reserve", fontSize = 15.sp)},
                    onClick = {viewModel.reserve(it.id)}
                )
            } else {
                Text(
                    text = "${it.numberInCart} items reserved",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W200
                )
                Spacer(modifier = Modifier.height(7.dp))
                Button(
                    content = {Text(text = "Return", fontSize = 15.sp)},
                    onClick = {viewModel.returnItem(it.id)}
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(12.dp))
}