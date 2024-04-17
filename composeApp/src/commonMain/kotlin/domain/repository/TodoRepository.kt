package domain.repository

import domain.RequestState
import domain.TodoTask
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    fun readActiveTasks(): Flow<RequestState<List<TodoTask>>>
    fun readCompletedTasks(): Flow<RequestState<List<TodoTask>>>
    suspend fun addTask(task: TodoTask)
    suspend fun updateTask(task: TodoTask)
    suspend fun setCompleted(task: TodoTask, taskCompleted: Boolean)
    suspend fun setFavorite(task: TodoTask, isFavorite: Boolean)
    suspend fun deleteTask(task: TodoTask)
}