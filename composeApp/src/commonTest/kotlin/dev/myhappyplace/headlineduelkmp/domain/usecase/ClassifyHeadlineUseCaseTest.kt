package dev.myhappyplace.headlineduelkmp.domain.usecase

import dev.myhappyplace.headlineduelkmp.domain.model.ClassificationResult
import dev.myhappyplace.headlineduelkmp.domain.repository.NewsClassifierRepository
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ClassifyHeadlineUseCaseTest {

    private val fakeRepo = object : NewsClassifierRepository {
        override suspend fun classifyHeadline(text: String): ClassificationResult {
            // Simple fake logic: return a fixed result if the text matches, else empty
            return if (text == "NASA launches rocket") {
                ClassificationResult("Sci/Tech", 0.97)
            } else {
                ClassificationResult("Unknown", 0.0)
            }
        }
    }

    private val useCase = ClassifyHeadlineUseCase(fakeRepo)

    @Test
    fun `When use case is called Then it returns correct classification`() = runTest {
        val headlineToTest = "NASA launches rocket"

        val result = useCase(headlineToTest)

        assertEquals("Sci/Tech", result.label)
        assertEquals(0.97, result.score, 0.001)
    }
}