package dev.myhappyplace.headlineduelkmp.data.repository

import dev.myhappyplace.headlineduelkmp.data.datasource.HeadlineDataSource
import dev.myhappyplace.headlineduelkmp.domain.model.Headline
import dev.myhappyplace.headlineduelkmp.domain.repository.NewsHeadlineRepository


class NewsHeadlineRepositoryImpl(private val headlineDataSource: HeadlineDataSource) :
    NewsHeadlineRepository {
    override suspend fun getHeadline(excludedIndices: Set<Int>): Headline {
        return headlineDataSource.getHeadline(excludedIndices)
    }
}
