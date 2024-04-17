package di

import data.local.LocalDataSource
import data.local.LocalDataSourceImpl
import data.local.ToDoTask
import io.realm.kotlin.Configuration
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import org.koin.dsl.module

val dataModule = module {
    single<Configuration> {
        RealmConfiguration.Builder(
            schema = setOf(ToDoTask::class)
        ).compactOnLaunch().build()
    }
    single<Realm> {
        Realm.open(get())
    }

    single<LocalDataSource> {
        LocalDataSourceImpl(
            realm = get()
        )
    }
}