package ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import di.ViewModelFac
import di.ViewModelFac.sharedViewModel
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
        startDestination = Route.RootNavigation.route
    ) {
        navigation(
            route = Route.RootNavigation.route,
            startDestination = Route.HomeScreen.route,
        ) {
            composable(
                route = Route.HomeScreen.route
            ) {entry ->
                val homeViewModel = ViewModelFac.getHomeViewModel()
                val sharedViewModel = entry.sharedViewModel(navController)
                val state by homeViewModel.state.collectAsState()

                HomeScreen(
                    state = state,
                    navigateToTask = {
                        sharedViewModel.set(it)
                        navController.navigate(Route.TaskScreen.route)
                    }
                )
            }

            composable(
                route = Route.TaskScreen.route,
//            arguments = listOf(
//                navArgument(Route.ARGUMENT_TASK_ID) {
//                    type = NavType.StringType
//                    nullable = true
//                }
//            )

            ) {entry ->
                val sharedViewModel = entry.sharedViewModel(navController)
                val currentTask by sharedViewModel.currentTask.collectAsState()
                val taskViewModel = ViewModelFac.getTaskViewModel(currentTask)
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
}