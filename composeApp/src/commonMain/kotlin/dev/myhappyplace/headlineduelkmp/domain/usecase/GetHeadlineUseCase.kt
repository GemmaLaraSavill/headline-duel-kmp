package dev.myhappyplace.headlineduelkmp.domain.usecase

import dev.myhappyplace.headlineduelkmp.domain.model.Headline
import dev.myhappyplace.headlineduelkmp.domain.repository.NewsHeadlineRepository


class GetHeadlineUseCase(private val newsHeadlineRepository: NewsHeadlineRepository) {
    suspend operator fun invoke(excludedIndices: Set<Int>): Headline {
        return newsHeadlineRepository.getHeadline(excludedIndices)
    }
}