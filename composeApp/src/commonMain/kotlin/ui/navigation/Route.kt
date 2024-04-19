package ui.navigation


sealed class Route(val route: String, title: String) {

    companion object {
        const val ARGUMENT_TASK_ID = "taskId"
    }

    data object RootNavigation: Route("RootNavigation", "Root")
    data object HomeScreen: Route("HomeScreen", "Home")
    data object TaskScreen: Route("TaskScreen", "Home")
//    data object TaskScreen: Route("TaskScreen?taskId={$ARGUMENT_TASK_ID}", "Task") {
//        fun passId(id: String? = null): String {
//            id?.let {
//                return this.route.replace(oldValue = "{$ARGUMENT_TASK_ID}", newValue = it)
//            } ?: return this.route
//        }
//    }
}