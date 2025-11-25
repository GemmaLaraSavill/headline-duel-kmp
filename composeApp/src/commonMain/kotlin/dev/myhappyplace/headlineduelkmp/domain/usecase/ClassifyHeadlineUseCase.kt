package dev.myhappyplace.headlineduelkmp.domain.usecase

import dev.myhappyplace.headlineduelkmp.domain.model.ClassificationResult
import dev.myhappyplace.headlineduelkmp.domain.repository.NewsClassifierRepository


class ClassifyHeadlineUseCase(
    private val repository: NewsClassifierRepository
) {
    suspend operator fun invoke(text: String): ClassificationResult {
        return repository.classifyHeadline(text)
    }
}