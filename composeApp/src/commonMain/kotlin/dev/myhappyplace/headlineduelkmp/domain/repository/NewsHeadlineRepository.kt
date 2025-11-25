package dev.myhappyplace.headlineduelkmp.domain.repository

import dev.myhappyplace.headlineduelkmp.domain.model.Headline


interface NewsHeadlineRepository {
    suspend fun getHeadline(excludedIndices: Set<Int>): Headline
}