package com.fahad.todoapp.ui.navgtion

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fahad.todoapp.ui.screen.Screen
import com.fahad.todoapp.ui.screen.SplashScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "Splash") {
        composable("Splash") {
            SplashScreen(navController = navController, )
        }

        composable("Home") {
            Screen()
        }
    }

}

