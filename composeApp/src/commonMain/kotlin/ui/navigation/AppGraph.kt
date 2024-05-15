package ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import di.koinViewModel
import di.sharedViewModel
import org.koin.core.parameter.parametersOf
import ui.screens.SharedViewModel
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
                val homeViewModel = koinViewModel<HomeViewModel>()
                val sharedViewModel = entry.sharedViewModel<SharedViewModel>(navController)
                val state by homeViewModel.state.collectAsStateWithLifecycle()

                HomeScreen(
                    state = state,
                    navigateToTask = {
                        sharedViewModel.set(it)
                        navController.navigate(Route.TaskScreen.route)
                    },
                    onEvent = homeViewModel::onEvent
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
                val sharedViewModel = entry.sharedViewModel<SharedViewModel>(navController)
                val currentTask by sharedViewModel.currentTask.collectAsStateWithLifecycle()
                val taskViewModel = koinViewModel<TaskViewModel>(
                    parameters = { parametersOf(currentTask) }
                )
                val state by taskViewModel.state.collectAsStateWithLifecycle()

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