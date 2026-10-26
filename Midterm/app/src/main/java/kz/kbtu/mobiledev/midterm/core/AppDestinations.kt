package kz.kbtu.mobiledev.midterm.core

import kz.kbtu.mobiledev.midterm.R

enum class AppDestinations(
    val label: String,
    val icon: Int,
) {
    HOME("Home", R.drawable.ic_home),
    ADD("Add Item", R.drawable.ic_account_box),
    CART("Cart", R.drawable.ic_favorite),
}