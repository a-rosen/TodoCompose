package com.example.todocompose.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.todocompose.navigation.TodoNavGraph

@Composable
fun AppScreenContainer(navController: NavHostController = rememberNavController()) {
    TodoNavGraph(navController = navController)
}