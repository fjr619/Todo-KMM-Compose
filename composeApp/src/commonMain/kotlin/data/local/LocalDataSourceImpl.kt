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
    override fun readActiveTasks(): Flow<List<ToDoTask>> {
        return realm.query<ToDoTask>(query = "completed == $0", false)
//            .sort(property = "favorite", sortOrder = Sort.DESCENDING)
            .asFlow()
            .map {
                it.list
            }
//            .map { result ->
//                RequestState.Success(
//                    data = result.list
////                        .sortedByDescending {
////                            toDoTask -> toDoTask.favorite
////                    }
//                )
//            }
    }

    override fun readCompletedTasks(): Flow<List<ToDoTask>> {
        return realm.query<ToDoTask>(query = "completed == $0", true)
            .asFlow()
            .map {
                it.list
            }
//            .map { result ->
//                RequestState.Success(
//                    data = result.list.map { it.toDomain() }
//                )
//            }
    }

    override suspend fun addTask(task: ToDoTask) {
        realm.write {
            copyToRealm(task)
        }
    }

    override suspend fun updateTask(task: ToDoTask) {
        realm.write {
            try {
                val queriedTask = query<ToDoTask>("_id == $0", task._id).first().find()
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

    override suspend fun setCompleted(task: ToDoTask, taskCompleted: Boolean) {
        realm.write {
            try {
                val queriedTask = query<ToDoTask>("_id == $0", task._id).first().find()
                queriedTask?.apply {
                    this.completed = taskCompleted
                }
            } catch (e: Exception) {
                println(e)
            }
        }
    }

    override suspend fun setFavorite(task: ToDoTask, isFavorite: Boolean) {
        realm.write {
            try {
                val queriedTask = query<ToDoTask>("_id == $0", task._id).first().find()
                queriedTask?.apply {
                    this.favorite = isFavorite
                }
            } catch (e: Exception) {
                println(e)
            }
        }
    }

    override suspend fun deleteTask(task: ToDoTask) {
        realm.write {
            try {
                val queriedTask = query<ToDoTask>("_id == $0", task._id).first().find()
                queriedTask?.apply {
                    delete(this)
                }
            } catch (e: Exception) {
                println(e)
            }
        }
    }
}