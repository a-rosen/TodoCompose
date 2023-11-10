package com.example.todocompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.todocompose.ui.screens.AppScreenContainer
import com.example.todocompose.ui.theme.TodoComposeTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TodoComposeTheme {
                AppScreenContainer()
            }
        }
    }
}

// NEXT WEEK: beginning of nav is added, next is to implement it (you have the graph, now create actual ways to use it in your components)
// HELPFUL RESOURCES:
/*
https://developer.android.com/jetpack/compose/libraries#hilt-navigation Discusses the need to add a separate library for hilt & nav-compose to work together
https://github.com/google-developer-training/basic-android-kotlin-compose-training-inventory-app/ Example inventory app with clearer nav than cupcake example
https://developer.android.com/codelabs/basic-android-kotlin-compose-navigation Cupcake example
https://developer.android.com/jetpack/compose/navigation Quick link to docs

 */