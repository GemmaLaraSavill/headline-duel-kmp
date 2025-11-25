package dev.myhappyplace.headlineduelkmp.di

import dev.myhappyplace.headlineduelkmp.ui.viewmodel.HeadlineViewModel
import io.ktor.client.engine.darwin.Darwin
import org.koin.core.module.Module
import org.koin.dsl.module

actual val headlineDuelModule: Module = module {
    single { Darwin.create() }
    factory {
        HeadlineViewModel(get(), get())
    }
}