package hu.ait.aitforum.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import hu.ait.aitforum.ui.screen.login.LoginScreen
import hu.ait.aitforum.ui.screen.mainmessages.MainScreen
import hu.ait.aitforum.ui.screen.writemessage.WritePostScreen

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Login.route) {
            LoginScreen(onLoginSuccess = {
                // navigate to the main messages screen
                navController.navigate(Screen.Main.route)
            })
        }
        composable(Screen.Main.route) {
            MainScreen(
                onWriteNewPostClick = {
                    navController.navigate(Screen.WritePost.route)
                }
            )
        }
        composable(Screen.WritePost.route) {
            WritePostScreen(
                onWritePostSuccess = {
                    //navController.navigate(Screen.Main.route)
                    navController.popBackStack(Screen.Main.route,
                        false)
                }
            )
        }
    }
}