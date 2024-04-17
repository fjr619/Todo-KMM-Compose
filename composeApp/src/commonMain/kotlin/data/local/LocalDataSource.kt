package data.local

import domain.RequestState
import domain.TodoTask
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun readActiveTasks(): Flow<RequestState<List<TodoTask>>>
    fun readCompletedTasks(): Flow<RequestState<List<ToDoTask>>>
    suspend fun addTask(task: ToDoTask)
    suspend fun updateTask(task: ToDoTask)
    suspend fun setCompleted(task: ToDoTask, taskCompleted: Boolean)
    suspend fun setFavorite(task: ToDoTask, isFavorite: Boolean)
    suspend fun deleteTask(task: ToDoTask)
}