package ui.screens.task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text2.TextFieldDecorator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TextFieldDefaults.TextFieldDecorationBox
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

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

                  BasicTextField(
                      textStyle = TextStyle(
                          color = MaterialTheme.colorScheme.onSurface,
                          fontSize = MaterialTheme.typography.titleLarge.fontSize
                      ),
                      singleLine = true,
                      value = state.currentTitle,
                      onValueChange = {
                          onTaskEvent(TaskEvent.SetTitle(it))
                      },
                      decorationBox = {
                          if (state.currentTitle.isEmpty()) {
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
        }
    ) {

    }
}