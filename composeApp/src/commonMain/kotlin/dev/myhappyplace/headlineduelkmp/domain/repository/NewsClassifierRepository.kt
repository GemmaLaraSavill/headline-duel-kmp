package dev.myhappyplace.headlineduelkmp.domain.repository

import dev.myhappyplace.headlineduelkmp.domain.model.ClassificationResult


interface NewsClassifierRepository {
    suspend fun classifyHeadline(text: String): ClassificationResult
}