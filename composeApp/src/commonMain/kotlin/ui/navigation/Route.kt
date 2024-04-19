package ui.navigation

sealed class Route(val route: String, title: String) {
    data object HomeScreen: Route("HomeScreen", "Home")
    data object TaskScreen: Route("TaskScreen", "Task")
}