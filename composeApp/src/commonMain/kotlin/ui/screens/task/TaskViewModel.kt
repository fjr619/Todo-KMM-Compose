package ui.screens.task

import androidx.lifecycle.LifecycleDestroyedException
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.TodoTask
import domain.repository.TodoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.mongodb.kbson.ObjectId
import ui.screens.task.TaskViewModel.Companion.DEFAULT_DESCRIPTION
import ui.screens.task.TaskViewModel.Companion.DEFAULT_TITLE

sealed class TaskEvent {
    data class Add(val task: TodoTask) : TaskEvent()
    data class Update(val task: TodoTask) : TaskEvent()
    data class SetTitle(val title: String): TaskEvent()
    data class SetDesc(val desc: String): TaskEvent()
}

data class TaskUiState(
    val currentTitle: String = "",
    val currentDescription: String = "",
    val currentTask: TodoTask? = null
)

class TaskViewModel(
    private val currentTaskId: ObjectId? = null,
    private val repository: TodoRepository
) : ViewModel() {

    private val _state = MutableStateFlow(TaskUiState())
    val state = _state.asStateFlow()

    companion object {
        const val DEFAULT_TITLE = "Enter the Title"
        const val DEFAULT_DESCRIPTION = "Add some description"
    }
    
    fun onEvent(event: TaskEvent) {
        when(event) {
            is TaskEvent.SetTitle -> {
                _state.update {
                    it.copy(
                        currentTitle = event.title
                    )
                }
            }
            is TaskEvent.SetDesc -> {
                _state.update {
                    it.copy(
                        currentDescription = event.desc
                    )
                }
            }
            is TaskEvent.Add -> {
                viewModelScope.launch {
                    repository.addTask(event.task)
                }
            }
            is TaskEvent.Update -> {
                viewModelScope.launch {
                    repository.updateTask(event.task)
                }
            }
        }
    }


}