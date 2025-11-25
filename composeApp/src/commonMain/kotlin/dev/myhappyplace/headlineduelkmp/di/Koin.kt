package dev.myhappyplace.headlineduelkmp.di

import dev.myhappyplace.headlineduelkmp.data.datasource.HeadlineDataSource
import dev.myhappyplace.headlineduelkmp.data.datasource.LocalHeadlineDataSource
import dev.myhappyplace.headlineduelkmp.data.datasource.NewsClassifierRemoteDataSource
import dev.myhappyplace.headlineduelkmp.data.datasource.NewsClassifierRemoteDataSourceImpl
import dev.myhappyplace.headlineduelkmp.data.network.KtorClientProvider
import dev.myhappyplace.headlineduelkmp.data.repository.NewsClassifierRepositoryImpl
import dev.myhappyplace.headlineduelkmp.data.repository.NewsHeadlineRepositoryImpl
import dev.myhappyplace.headlineduelkmp.domain.repository.NewsClassifierRepository
import dev.myhappyplace.headlineduelkmp.domain.repository.NewsHeadlineRepository
import dev.myhappyplace.headlineduelkmp.domain.usecase.ClassifyHeadlineUseCase
import dev.myhappyplace.headlineduelkmp.domain.usecase.GetHeadlineUseCase
import io.ktor.client.HttpClient
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

expect val headlineDuelModule: Module

val sharedModule = module {
    single<HttpClient> { KtorClientProvider.createHttpClient(get()) }
    single<NewsClassifierRemoteDataSource> { NewsClassifierRemoteDataSourceImpl(get()) }
    single<NewsClassifierRepository> { NewsClassifierRepositoryImpl(get()) }
    factory { ClassifyHeadlineUseCase(get()) }
    factory { GetHeadlineUseCase(get()) }
    single<HeadlineDataSource> { LocalHeadlineDataSource() }
    single<NewsHeadlineRepository> { NewsHeadlineRepositoryImpl(get()) }
}

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(sharedModule, headlineDuelModule)
    }
}