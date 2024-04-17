package data.local

import domain.RequestState
import domain.TodoTask
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.Sort
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class LocalDataSourceImpl(
    private val realm: Realm
): LocalDataSource {
    override fun readActiveTasks(): Flow<RequestState<List<TodoTask>>> {
        return realm.query<ToDoTask>(query = "completed == $0", false)
            .sort(property = "favorite", sortOrder = Sort.DESCENDING)
            .asFlow()
            .map { result ->
                RequestState.Success(
                    data = result.list
//                        .sortedByDescending {
//                            toDoTask -> toDoTask.favorite
//                    }
                        .map {
                        it.toDomain()
                    }
                )
            }
    }

    override fun readCompletedTasks(): Flow<RequestState<List<TodoTask>>> {
        return realm.query<ToDoTask>(query = "completed == $0", true)
            .asFlow()
            .map { result ->
                RequestState.Success(
                    data = result.list.map { it.toDomain() }
                )
            }
    }

    override suspend fun addTask(task: TodoTask) {
        realm.write {
            copyToRealm(task.toData())
        }
    }

    override suspend fun updateTask(task: TodoTask) {
        realm.write {
            try {
                val queriedTask = query<ToDoTask>("_id == $0", task.id).first().find()
                queriedTask?.apply {
                    this.title = task.title
                    this.description = task.description
                }
//                queriedTask?.let {
//                    findLatest(it)?.let { currentTask ->
//                        currentTask.title = task.title
//                        currentTask.description = task.description
//                    }
//                }
            } catch (e: Exception) {
                println(e)
            }
        }
    }

    override suspend fun setCompleted(task: TodoTask, taskCompleted: Boolean) {
        realm.write {
            try {
                val queriedTask = query<ToDoTask>("_id == $0", task.id).first().find()
                queriedTask?.apply {
                    this.completed = taskCompleted
                }
            } catch (e: Exception) {
                println(e)
            }
        }
    }

    override suspend fun setFavorite(task: TodoTask, isFavorite: Boolean) {
        realm.write {
            try {
                val queriedTask = query<ToDoTask>("_id == $0", task.id).first().find()
                queriedTask?.apply {
                    this.favorite = isFavorite
                }
            } catch (e: Exception) {
                println(e)
            }
        }
    }

    override suspend fun deleteTask(task: TodoTask) {
        realm.write {
            try {
                val queriedTask = query<ToDoTask>("_id == $0", task.id).first().find()
                queriedTask?.apply {
                    delete(this)
                }
            } catch (e: Exception) {
                println(e)
            }
        }
    }
}