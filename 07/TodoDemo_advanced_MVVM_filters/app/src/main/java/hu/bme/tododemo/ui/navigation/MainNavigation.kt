package hu.bme.tododemo.ui.navigation

sealed class MainNavigation(val route: String) {
    object MainTodoScreen : MainNavigation("maintodoscreen")
}

