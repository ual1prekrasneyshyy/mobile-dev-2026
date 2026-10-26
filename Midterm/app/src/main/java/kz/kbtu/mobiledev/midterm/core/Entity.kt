package kz.kbtu.mobiledev.midterm.core

data class Item(
    val id: Int,
    val code: String,
    val name: String,
    val price: Int,
    val numberOfAvailable: Int,
    val numberInCart: Int,
)