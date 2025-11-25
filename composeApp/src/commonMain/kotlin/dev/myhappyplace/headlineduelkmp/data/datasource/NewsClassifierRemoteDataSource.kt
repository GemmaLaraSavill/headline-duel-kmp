package dev.myhappyplace.headlineduelkmp.data.datasource

import dev.myhappyplace.headlineduelkmp.domain.model.ClassificationResult


interface NewsClassifierRemoteDataSource {
    suspend fun classifyHeadline(text: String): ClassificationResult
}