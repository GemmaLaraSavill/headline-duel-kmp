package dev.myhappyplace.headlineduelkmp.data.repository

import dev.myhappyplace.headlineduelkmp.data.datasource.NewsClassifierRemoteDataSource
import dev.myhappyplace.headlineduelkmp.domain.repository.NewsClassifierRepository

class NewsClassifierRepositoryImpl(
    private val remoteDataSource: NewsClassifierRemoteDataSource
) : NewsClassifierRepository {
    override suspend fun classifyHeadline(text: String) =
        remoteDataSource.classifyHeadline(text)
}