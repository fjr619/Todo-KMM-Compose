package ui.screens.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.TodoTask
import domain.repository.TodoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.mongodb.kbson.BsonObjectId

sealed class TaskEvent {
    data object Add : TaskEvent()
    data object Update : TaskEvent()
    data class SetTitle(val title: String) : TaskEvent()
    data class SetDesc(val desc: String) : TaskEvent()
}

data class TaskUiState(
    val currentTask: TodoTask = TodoTask()
)

class TaskViewModel(
    private val selectedTask: TodoTask,
    private val repository: TodoRepository
) : ViewModel() {

    private val _state = MutableStateFlow(TaskUiState())
    val state = _state.asStateFlow()

    companion object {
        const val DEFAULT_TITLE = "Enter the Title"
        const val DEFAULT_DESCRIPTION = "Add some description"
    }

    init {
        println("get data for ${selectedTask.id?.toHexString()}")
        _state.update {
            it.copy(
                currentTask = selectedTask
            )
        }
    }

    fun onEvent(event: TaskEvent) {
        when (event) {
            is TaskEvent.SetTitle -> {
                _state.update {
                    it.copy(
                        currentTask = it.currentTask.copy(
                            title = event.title
                        )
                    )
                }
            }

            is TaskEvent.SetDesc -> {
                _state.update {
                    it.copy(
                        currentTask = it.currentTask.copy(
                            description = event.desc
                        )
                    )
                }
            }

            is TaskEvent.Add -> {
                state.onEach {
                    repository.addTask(it.currentTask)
                }.launchIn(viewModelScope)
            }

            is TaskEvent.Update -> {
                state.onEach {
                    repository.updateTask(it.currentTask)
                }.launchIn(viewModelScope)
            }
        }
    }


}