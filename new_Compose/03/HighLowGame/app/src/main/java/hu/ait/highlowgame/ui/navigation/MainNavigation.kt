package hu.ait.highlowgame.ui.navigation

sealed class MainNavigation(val route: String) {
    object MainScreen: MainNavigation("mainscreen")
    object GameScreen: MainNavigation("gamescreen?upperBound={upperBound}") {
        fun createRoute(upperBound: Int) : String {
            return "gamescreen?upperBound=$upperBound"
        }
    }
}