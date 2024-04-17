package data.local

import domain.RequestState
import domain.TodoTask
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun readActiveTasks(): Flow<List<ToDoTask>>
    fun readCompletedTasks(): Flow<List<ToDoTask>>
    suspend fun addTask(task: ToDoTask)
    suspend fun updateTask(task: ToDoTask)
    suspend fun setCompleted(task: ToDoTask, taskCompleted: Boolean)
    suspend fun setFavorite(task: ToDoTask, isFavorite: Boolean)
    suspend fun deleteTask(task: ToDoTask)
}