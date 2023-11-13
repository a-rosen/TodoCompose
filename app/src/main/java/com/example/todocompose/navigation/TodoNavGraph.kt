package com.example.todocompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.todocompose.ui.screens.DetailScreen
import com.example.todocompose.ui.screens.DetailsDestination
import com.example.todocompose.ui.screens.TodoListScreen

@Composable
fun TodoNavGraph(
    navController: NavHostController,
    startDestination: String = "TodoListScreen"
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable("TodoListScreen") {
            TodoListScreen(
                navigateToDetailScreen = {
                    navController.navigate("DetailScreen/${it}")
                }
            )
        }
        composable(
            route = DetailsDestination.routeWithArgs,
            arguments = listOf(
                navArgument(DetailsDestination.itemIdArg)
                { type = NavType.IntType }
            )
        ) { DetailScreen() }
    }
}
