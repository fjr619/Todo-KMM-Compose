package ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.RequestState
import domain.TodoTask
import domain.repository.TodoRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class HomeUiState(
    val activeTask: RequestState<List<TodoTask>> = RequestState.Idle,
    val completedTask: RequestState<List<TodoTask>> = RequestState.Idle,
)

sealed class HomeEvent {
    data object GetData : HomeEvent()

    //    data class Add(val task: TodoTask) : HomeEvent()
//    data class Update(val task: TodoTask) : HomeEvent()
    data class Delete(val task: TodoTask) : HomeEvent()
    data class SetCompleted(val task: TodoTask, val completed: Boolean) : HomeEvent()
    data class SetFavorite(val task: TodoTask, val isFavorite: Boolean) : HomeEvent()
}

class HomeViewModel(
    private val repository: TodoRepository
) : ViewModel() {

    private val _state = MutableStateFlow(HomeUiState())
    val state = _state.asStateFlow()

    init {
        println("INIT HOME VIEWMODEL")
        onEvent(HomeEvent.GetData)
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.GetData -> {
                _state.update {
                    it.copy(
                        activeTask = RequestState.Loading,
                        completedTask = RequestState.Loading
                    )
                }
                viewModelScope.launch {
                    delay(500)
                    combine(
                        repository.readActiveTasks(),
                        repository.readCompletedTasks()
                    ) { active, completed ->
                        Pair(active, completed)
                    }.collect { result ->
                        _state.update {
                            it.copy(
                                activeTask = result.first,
                                completedTask = result.second
                            )
                        }
                    }
                }
            }

            is HomeEvent.Delete -> {
                viewModelScope.launch {
                    repository.deleteTask(event.task)
                }
            }

            is HomeEvent.SetFavorite -> {
                viewModelScope.launch {
                    repository.setFavorite(event.task, event.isFavorite)
                }
            }

            is HomeEvent.SetCompleted -> {
                viewModelScope.launch {
                    repository.setCompleted(event.task, event.completed)
                }
            }
        }
    }

}