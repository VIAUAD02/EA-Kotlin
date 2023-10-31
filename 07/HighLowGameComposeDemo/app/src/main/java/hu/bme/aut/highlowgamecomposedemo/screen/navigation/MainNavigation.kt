package hu.bme.aut.highlowgamecomposedemo.screen.navigation

sealed class MainNavigation(val route: String) {
    companion object {
        const val ARG_UPPERBOUND = "upperBound"
        const val ARG_HELPTEXT = "helptext"
    }

    object MainScreen: MainNavigation("mainscreen")
    object GameScreen : MainNavigation("gamescreen?$ARG_UPPERBOUND={$ARG_UPPERBOUND}"){
        fun createRoute(upperBound: Int) = "gamescreen?$ARG_UPPERBOUND=$upperBound"
    }
    object AboutScreen: MainNavigation("aboutscreen")
    object HelpScreen: MainNavigation("helpscreen/{$ARG_HELPTEXT}") {
        fun createRouter(helpText: String) = "helpscreen/$helpText"
    }
}