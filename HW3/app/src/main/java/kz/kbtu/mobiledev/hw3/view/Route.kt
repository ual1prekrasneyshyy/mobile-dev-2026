package kz.kbtu.mobiledev.hw3.view

sealed class Route (val route: String) {
    object Home: Route("home")
    object Add: Route("add")
    object Update: Route("update")
    object View: Route("view")
}