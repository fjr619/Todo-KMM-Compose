package ui.screens.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import domain.RequestState
import domain.TodoTask
import ui.screens.components.ErrorScreen
import ui.screens.components.LoadingScreen

@Composable
fun DisplayTasks(
    modifier: Modifier,
    tasks: RequestState<List<TodoTask>>,
    showActive: Boolean = true,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 12.dp),
            text = if (showActive) "Active Tasks" else "Completed Tasks",
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(12.dp))
        tasks.DisplayResult(
            onLoading = {
                LoadingScreen()
            },
            onError = {
                ErrorScreen(message = it)
            },
            onSuccess = { listTodo ->
                if (listTodo.isNotEmpty()) {
                    LazyColumn(
                        modifier = Modifier.padding(horizontal = 24.dp)
                    ) {
                        items(
                            items = listTodo,
                            key = { task -> task.id.toHexString() }
                        ) { task ->
                            TaskView(
                                showActive = showActive,
                                task = task,
                                onSelect = { },
                                onComplete = { selectedTask, completed ->

                                },
                                onFavorite = { selectedTask, favorite ->

                                },
                                onDelete = { selectedTask ->

                                }
                            )
                        }
                    }
                } else {
                    ErrorScreen()
                }
            },
        )
    }
}