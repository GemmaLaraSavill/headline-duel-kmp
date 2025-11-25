package dev.myhappyplace.headlineduelkmp.ui.viewmodel

import dev.myhappyplace.headlineduelkmp.domain.model.ClassificationResult

data class HeadlineUiState(
    val headline: String = "",
    val userAnswer: String? = null,
    val modelResult: ClassificationResult? = null,
    val isLoading: Boolean = false,
    val correctClassification: String? = null,
    val error: HeadlineError? = null
)