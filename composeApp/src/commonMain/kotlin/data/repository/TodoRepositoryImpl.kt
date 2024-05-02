package data.repository

import data.local.LocalDataSource
import data.local.toData
import data.local.toDomain
import domain.RequestState
import domain.TodoTask
import domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

class TodoRepositoryImpl(
    private val localDataSource: LocalDataSource
): TodoRepository {
    override fun readActiveTasks(): Flow<RequestState<List<TodoTask>>> {
        return localDataSource.readActiveTasks().map {
            if (it.isNotEmpty()) {
                RequestState.Success(
                    data = it.map { result ->
                        result.toDomain()
                    }
                )
            } else {
                RequestState.Error("empty data")
            }

        }
    }

    override fun readCompletedTasks(): Flow<RequestState<List<TodoTask>>> {
        return localDataSource.readCompletedTasks().map {
            if (it.isNotEmpty()) {
                RequestState.Success(
                    data = it.map { result ->
                        result.toDomain()
                    }
                )
            } else {
                RequestState.Error("empty data")
            }

        }
    }

    override suspend fun addTask(task: TodoTask) = localDataSource.addTask(task.toData())

    override suspend fun updateTask(task: TodoTask) = localDataSource.updateTask(task.toData())

    override suspend fun setCompleted(task: TodoTask, taskCompleted: Boolean) =
        localDataSource.setCompleted(task.toData(), taskCompleted)

    override suspend fun setFavorite(task: TodoTask, isFavorite: Boolean) =
        localDataSource.setFavorite(task.toData(), isFavorite)

    override suspend fun deleteTask(task: TodoTask) = localDataSource.deleteTask(task.toData())
}