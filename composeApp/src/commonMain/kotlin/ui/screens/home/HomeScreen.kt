package ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import domain.TodoTask
import ui.screens.home.components.DisplayTasks


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    state: HomeUiState,
    navigateToTask: (TodoTask) -> Unit,
    onEvent: (HomeEvent) -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text(text = "Home") })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navigateToTask(TodoTask())
                },
                shape = RoundedCornerShape(12.dp),
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Icon"
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues)
        ) {
            DisplayTasks(
                modifier = Modifier.weight(1f),
                tasks = state.activeTask,
                onSelect = {
                    navigateToTask(it)
                },
                onFavorite = { task, favorite -> onEvent(HomeEvent.SetFavorite(task, favorite))},
                onComplete = { task, complete -> onEvent(HomeEvent.SetCompleted(task, complete))}
            )

            Spacer(modifier = Modifier.height(24.dp))

            DisplayTasks(
                modifier = Modifier.weight(1f),
                tasks = state.completedTask,
                showActive = false,
                onComplete = { task, complete -> onEvent(HomeEvent.SetCompleted(task, complete))}
            )
        }
    }
}