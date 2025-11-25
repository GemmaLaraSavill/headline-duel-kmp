package dev.myhappyplace.headlineduelkmp.di

import dev.myhappyplace.headlineduelkmp.ui.viewmodel.HeadlineViewModel
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.core.module.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module

actual val headlineDuelModule: Module = module {
    single { OkHttp.create() }
    viewModelOf(::HeadlineViewModel)
}