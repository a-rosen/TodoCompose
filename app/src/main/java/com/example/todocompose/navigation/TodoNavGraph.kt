package com.example.todocompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.todocompose.ui.screens.DetailScreen
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
        composable("TodoListScreen") { TodoListScreen() }
        composable("DetailScreen") { DetailScreen(item = null) }
    }
}
