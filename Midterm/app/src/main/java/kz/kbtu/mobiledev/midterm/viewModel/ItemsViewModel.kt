package kz.kbtu.mobiledev.midterm.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kz.kbtu.mobiledev.midterm.core.AppDestinations
import kz.kbtu.mobiledev.midterm.core.Item

class ItemsViewModel : ViewModel() {
    var currentDestination by mutableStateOf(AppDestinations.HOME)
    var addItemCode by mutableStateOf("")
    var addItemName by mutableStateOf("")
    var addItemPrice by mutableIntStateOf(0)
    var addItemCount by mutableIntStateOf(0)

    var searchQuery by mutableStateOf("")

    private val _items = MutableStateFlow(mutableListOf(
        Item(1, "SM25FE", "Samsung Galaxy S25 FE", 400000, 15, 0),
        Item(2, "ATGF17", "Asus Tuf Gaming F17", 600000, 5, 0),
        Item(3, "X70M310+", "Xiaomi 70mai M310 Plus", 30000, 10, 0),
    ))
    val items = _items.asStateFlow()

    fun setCode(code: String) {
        addItemCode = code
    }

    fun setName(name: String) {
        addItemName = name
    }

    fun setPrice(price: Int) {
        addItemPrice = price
    }

    fun setCount(count: Int) {
        addItemCount = count
    }

    fun addItem() {
        _items.value.add(Item(_items.value.size+1, addItemCode, addItemName, addItemPrice, addItemCount, 0))
        addItemCode = ""
        addItemName = ""
        addItemPrice = 0
        addItemCount = 0
        currentDestination = AppDestinations.HOME
    }

    fun reserve(id: Int){
        val newList = mutableListOf<Item>()

        for(i in _items.value.indices) {
            val current = _items.value[i]

            if(current.id == id) {
                if(current.numberOfAvailable > 0) {
                    newList.add(Item(
                        current.id,
                        current.code,
                        current.name,
                        current.price,
                        current.numberOfAvailable - 1,
                        current.numberInCart + 1
                    ))
                }else{
                    newList.add(current)
                }
            }else{
                newList.add(current)
            }
        }

        _items.update { newList }
    }

    fun returnItem(id: Int){
        val newList = mutableListOf<Item>()

        for(i in _items.value.indices) {
            val current = _items.value[i]

            if(current.id == id) {
                if(current.numberInCart > 0) {
                    newList.add(Item(
                        current.id,
                        current.code,
                        current.name,
                        current.price,
                        current.numberOfAvailable + 1,
                        current.numberInCart - 1
                    ))
                }else{
                    newList.add(current)
                }
            }else{
                newList.add(current)
            }
        }

        _items.update { newList }
    }
}