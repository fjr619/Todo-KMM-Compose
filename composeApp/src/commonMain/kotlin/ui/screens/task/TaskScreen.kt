package ui.screens.task

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import domain.TodoTask

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(
    state: TaskUiState,
    onNavigateUp: () -> Unit,
    onTaskEvent: (TaskEvent) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    //https://rivaldy.medium.com/jetpack-compose-customize-your-searchbar-with-basictextfield-c1cdcbd3e3aa
                    BasicTextField(
                        textStyle = TextStyle(
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = MaterialTheme.typography.titleLarge.fontSize
                        ),
                        singleLine = true,
                        value = state.currentTask.title,
                        onValueChange = {
                            onTaskEvent(TaskEvent.SetTitle(it))
                        },
                        decorationBox = {
                            if (state.currentTask.title.isEmpty()) {
                                Text(
                                    text = TaskViewModel.DEFAULT_TITLE
                                )
                            }
                            it()
                        }
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onNavigateUp
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "Back Arrow"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            if (state.currentTask.title.isNotEmpty() && state.currentTask.description.isNotEmpty()) {
                FloatingActionButton(
                    onClick = {
                        if(state.currentTask.id == null) {
                            onTaskEvent(TaskEvent.Add)
                        } else {
                            onTaskEvent(TaskEvent.Update)
                        }

                        onNavigateUp()
                    },
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Checkmark Icon"
                    )
                }
            }
        }
    ) {
        BasicTextField(
            modifier = Modifier.fillMaxSize().padding(it).padding(horizontal = 24.dp),
            textStyle = TextStyle(
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                color = MaterialTheme.colorScheme.onSurface
            ),
            value = state.currentTask.description,
            onValueChange = { desc ->
                onTaskEvent(TaskEvent.SetDesc(desc))
            },
            decorationBox = {
                if (state.currentTask.description.isEmpty()) {
                    Text(
                        text = TaskViewModel.DEFAULT_DESCRIPTION,
                        color = Color.Gray.copy(alpha = 0.5f)
                    )
                }
                it()
            }
        )
    }
}