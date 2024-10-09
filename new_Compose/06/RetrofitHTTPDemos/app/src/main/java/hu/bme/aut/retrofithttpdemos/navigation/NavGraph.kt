package hu.bme.aut.retrofithttpdemos.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import hu.bme.aut.retrofithttpdemos.ui.screen.MainScreen
import hu.bme.aut.retrofithttpdemos.ui.screen.MoneyApiScreen
import hu.bme.aut.retrofithttpdemos.ui.screen.NasaMarsApiScreen

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Main.route
    ) {
        composable(Screen.Main.route) {
            MainScreen(
                onNasaMarsAPISelected = {
                // navigate to the main messages screen
                    navController.navigate(Screen.NasaMarsAPI.route)
                },
                onMoneyAPISelected = {
                    navController.navigate(Screen.MoneyRatesAPI.route)
                }
            )
        }
        composable(Screen.NasaMarsAPI.route) {
            NasaMarsApiScreen()
        }
        composable(Screen.MoneyRatesAPI.route) {
            MoneyApiScreen()
        }
    }
}


