package kz.kbtu.mobiledev.midterm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.lifecycle.viewmodel.compose.viewModel
import kz.kbtu.mobiledev.midterm.core.AppDestinations
import kz.kbtu.mobiledev.midterm.pages.AddItemPage
import kz.kbtu.mobiledev.midterm.pages.HomePage
import kz.kbtu.mobiledev.midterm.pages.CartPage
import kz.kbtu.mobiledev.midterm.ui.theme.MidtermTheme
import kz.kbtu.mobiledev.midterm.viewModel.ItemsViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MidtermTheme {
                MidtermApp()
            }
        }
    }
}

@PreviewScreenSizes
@Composable
fun MidtermApp(viewModel: ItemsViewModel = viewModel()) {
    NavigationSuiteScaffold(
        navigationSuiteItems = {
            AppDestinations.entries.forEach {
                item(
                    icon = {
                        Icon(
                            painterResource(it.icon),
                            contentDescription = it.label
                        )
                    },
                    label = { Text(it.label) },
                    selected = viewModel.currentDestination == it,
                    onClick = { viewModel.currentDestination = it }
                )
            }
        }
    ) {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Box(
                modifier = Modifier.padding(innerPadding)
            ) {
                when (viewModel.currentDestination) {
                    AppDestinations.HOME -> HomePage(viewModel)
                    AppDestinations.ADD -> AddItemPage(viewModel)
                    AppDestinations.CART -> CartPage(viewModel)
                }
            }
        }
    }
}





