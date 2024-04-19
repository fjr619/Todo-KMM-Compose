package ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import di.ViewModelFac
import ui.screens.home.HomeScreen
import ui.screens.home.HomeViewModel
import ui.screens.task.TaskScreen
import ui.screens.task.TaskViewModel

@Composable
fun AppGraph(
    navController: NavHostController = rememberNavController()
) {
    //https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-navigation-routing.html
    NavHost(
        navController = navController,
        startDestination = Route.HomeScreen.route
    ) {
        composable(
            route = Route.HomeScreen.route
        ) {
            val homeViewModel = ViewModelFac.getHomeViewModel(HomeViewModel::class)
            val state by homeViewModel.state.collectAsState()

            HomeScreen(
                state = state,
                navigateToTask = {
                    navController.navigate(Route.TaskScreen.route)
                }
            )
        }

        composable(
            route = Route.TaskScreen.route
        ) {
            val taskViewModel = ViewModelFac.getTaskViewModel(modelClass = TaskViewModel::class)
            val state by taskViewModel.state.collectAsState()
            TaskScreen(
                state = state,
                onNavigateUp = {
                    navController.navigateUp()
                },
                onTaskEvent = taskViewModel::onEvent
            )
        }
    }
}