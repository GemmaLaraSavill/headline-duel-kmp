package dev.myhappyplace.headlineduelkmp.data.datasource

import dev.myhappyplace.headlineduelkmp.domain.model.Headline


interface HeadlineDataSource {
    suspend fun getHeadline(excludedIndices: Set<Int>): Headline
}
