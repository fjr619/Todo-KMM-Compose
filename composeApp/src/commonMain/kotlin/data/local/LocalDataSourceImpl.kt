package data.local

import domain.RequestState
import domain.TodoTask
import io.realm.kotlin.Realm
import kotlinx.coroutines.flow.Flow

class LocalDataSourceImpl(
    private val realm: Realm
): LocalDataSource {
    override fun readActiveTasks(): Flow<RequestState<List<TodoTask>>> {
        TODO("Not yet implemented")
    }

    override fun readCompletedTasks(): Flow<RequestState<List<ToDoTask>>> {
        TODO("Not yet implemented")
    }

    override suspend fun addTask(task: ToDoTask) {
        TODO("Not yet implemented")
    }

    override suspend fun updateTask(task: ToDoTask) {
        TODO("Not yet implemented")
    }

    override suspend fun setCompleted(task: ToDoTask, taskCompleted: Boolean) {
        TODO("Not yet implemented")
    }

    override suspend fun setFavorite(task: ToDoTask, isFavorite: Boolean) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTask(task: ToDoTask) {
        TODO("Not yet implemented")
    }
}