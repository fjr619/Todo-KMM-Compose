package ui.screens

import androidx.lifecycle.ViewModel
import domain.TodoTask
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

//menggunakan shared view model navigation scope supaya bisa share data class
class SharedViewModel(
): ViewModel() {

    private val _currentTask: MutableStateFlow<TodoTask> = MutableStateFlow(TodoTask())
    val currentTask = _currentTask.asStateFlow()
    
    init {
        println("INI SHARED VIEW MODEL")
    }

    fun set(task: TodoTask) {
        _currentTask.update {
            task
        }
    }
}