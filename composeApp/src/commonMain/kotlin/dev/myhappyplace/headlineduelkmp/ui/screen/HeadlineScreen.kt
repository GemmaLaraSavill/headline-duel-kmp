package dev.myhappyplace.headlineduelkmp.ui.screen

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.myhappyplace.headlineduelkmp.ui.components.AnswerState
import dev.myhappyplace.headlineduelkmp.ui.components.ErrorState
import dev.myhappyplace.headlineduelkmp.ui.components.LoadingState
import dev.myhappyplace.headlineduelkmp.ui.components.QuestionState
import dev.myhappyplace.headlineduelkmp.ui.theme.ssp
import dev.myhappyplace.headlineduelkmp.ui.viewmodel.HeadlineError
import dev.myhappyplace.headlineduelkmp.ui.viewmodel.HeadlineUiState
import dev.myhappyplace.headlineduelkmp.ui.viewmodel.HeadlineViewModel
import headlineduelkmp.composeapp.generated.resources.Res
import headlineduelkmp.composeapp.generated.resources.app_name
import headlineduelkmp.composeapp.generated.resources.business
import headlineduelkmp.composeapp.generated.resources.error_generic_connection
import headlineduelkmp.composeapp.generated.resources.error_server_warming_up
import headlineduelkmp.composeapp.generated.resources.headline_prompt
import headlineduelkmp.composeapp.generated.resources.information
import headlineduelkmp.composeapp.generated.resources.sci_tech
import headlineduelkmp.composeapp.generated.resources.sports
import headlineduelkmp.composeapp.generated.resources.world
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun HeadlineScreen(viewModel: HeadlineViewModel, onNavigateToInfo: () -> Unit) {
    val state by viewModel.uiState.collectAsState()
    HeadlineScreenContent(
        state = state,
        onNavigateToInfo = onNavigateToInfo,
        onUserAnswer = viewModel::onUserAnswer,
        onNextHeadline = viewModel::nextHeadline,
        onRetry = viewModel::retry
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun HeadlineScreenContent(
    state: HeadlineUiState,
    onNavigateToInfo: () -> Unit,
    onUserAnswer: (String) -> Unit,
    onNextHeadline: () -> Unit,
    onRetry: () -> Unit
) {
    val uiAnimationState = when {
        state.isLoading -> HeadlineScreenAnimationState.Loading
        state.error != null -> HeadlineScreenAnimationState.Error
        state.modelResult == null -> HeadlineScreenAnimationState.Question
        else -> HeadlineScreenAnimationState.Answer
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        stringResource(Res.string.app_name),
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.displaySmall,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
                actions = {
                    IconButton(onClick = { onNavigateToInfo() }) {
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = stringResource(Res.string.information),
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                })
        }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(start = 16.dp, end = 16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(Res.string.headline_prompt),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 18.ssp,
                ),
                textAlign = TextAlign.Left,
                modifier = Modifier.padding(all = 16.dp)
            )
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp), colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                )
            ) {
                Text(
                    text = state.headline,
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(16.dp),
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }
            AnimatedContent(
                targetState = uiAnimationState, label = "UiStateAnimation", transitionSpec = {
                    if (targetState == HeadlineScreenAnimationState.Answer && initialState == HeadlineScreenAnimationState.Loading) {
                        fadeIn() togetherWith fadeOut()
                    } else {
                        fadeIn() togetherWith fadeOut()
                    }
                }) { targetUiState ->
                when (targetUiState) {
                    HeadlineScreenAnimationState.Loading -> LoadingState()
                    HeadlineScreenAnimationState.Question -> {
                        val categories = listOf(
                            Res.string.world, Res.string.sports, Res.string.business, Res.string.sci_tech
                        )
                        QuestionState(
                            categories = categories, onAnswer = onUserAnswer
                        )
                    }

                    HeadlineScreenAnimationState.Answer -> {
                        state.userAnswer?.let { userAnswer ->
                            state.modelResult?.let { modelResult ->
                                AnswerState(
                                    userAnswer = userAnswer,
                                    modelResult = modelResult,
                                    onNext = onNextHeadline,
                                    correctClassification = state.correctClassification
                                )
                            }
                        }
                    }

                    HeadlineScreenAnimationState.Error -> {
                        state.error?.let { error ->
                            val errorMessage = when (error) {
                                HeadlineError.SERVER_WARMING_UP -> stringResource(Res.string.error_server_warming_up)
                                HeadlineError.GENERIC_CONNECTION_ERROR -> stringResource(Res.string.error_generic_connection)
                            }
                            ErrorState(
                                error = errorMessage, onRetry = onRetry
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun HeadlineScreenPreview() {
    HeadlineScreenContent(
        state = HeadlineUiState(headline = "This is a sample headline for the preview."),
        onNavigateToInfo = {},
        onUserAnswer = {},
        onNextHeadline = {},
        onRetry = {}
    )
}