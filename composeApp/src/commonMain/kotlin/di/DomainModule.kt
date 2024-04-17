package di

import data.repository.TodoRepositoryImpl
import domain.repository.TodoRepository
import org.koin.dsl.module

val domainModule = module {
    factory<TodoRepository> { TodoRepositoryImpl(get()) }
}