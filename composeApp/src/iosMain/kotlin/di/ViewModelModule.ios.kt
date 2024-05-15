package di

import org.koin.core.module.Module
import org.koin.dsl.module
import ui.screens.SharedViewModel
import ui.screens.home.HomeViewModel
import ui.screens.task.TaskViewModel

actual val viewModelModule: Module = module {
    single { HomeViewModel(get()) }
    single { params -> TaskViewModel(params.get(), get()) }
    single { SharedViewModel() }
}