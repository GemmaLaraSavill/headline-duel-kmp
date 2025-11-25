package dev.myhappyplace.headlineduelkmp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.Sports
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.myhappyplace.headlineduelkmp.domain.model.ClassificationResult
import dev.myhappyplace.headlineduelkmp.ui.theme.CorrectAnswerBackgroundDark
import dev.myhappyplace.headlineduelkmp.ui.theme.CorrectAnswerBackgroundLight
import dev.myhappyplace.headlineduelkmp.ui.theme.CorrectAnswerTextDark
import dev.myhappyplace.headlineduelkmp.ui.theme.CorrectAnswerTextLight
import dev.myhappyplace.headlineduelkmp.ui.theme.WrongAnswerBackgroundDark
import dev.myhappyplace.headlineduelkmp.ui.theme.WrongAnswerBackgroundLight
import dev.myhappyplace.headlineduelkmp.ui.theme.WrongAnswerTextDark
import dev.myhappyplace.headlineduelkmp.ui.theme.WrongAnswerTextLight
import dev.myhappyplace.headlineduelkmp.ui.theme.ssp
import headlineduelkmp.composeapp.generated.resources.Res
import headlineduelkmp.composeapp.generated.resources.business
import headlineduelkmp.composeapp.generated.resources.correct_answer_user
import headlineduelkmp.composeapp.generated.resources.correct_classification
import headlineduelkmp.composeapp.generated.resources.model_answer
import headlineduelkmp.composeapp.generated.resources.next
import headlineduelkmp.composeapp.generated.resources.sci_tech
import headlineduelkmp.composeapp.generated.resources.sports
import headlineduelkmp.composeapp.generated.resources.world
import headlineduelkmp.composeapp.generated.resources.wrong_answer
import headlineduelkmp.composeapp.generated.resources.your_answer
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun QuestionState(categories: List<StringResource>, onAnswer: (String) -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        categories.forEach { res ->
            val category = stringResource(res)
            OutlinedButton(
                onClick = { onAnswer(category) }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                val icon = when (res) {
                    Res.string.world -> Icons.Default.Public
                    Res.string.sports -> Icons.Default.Sports
                    Res.string.business -> Icons.Default.Business
                    else -> Icons.Default.Science
                }
                Icon(
                    imageVector = icon,
                    contentDescription = stringResource(res),
                    modifier = Modifier.padding(8.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(stringResource(res), fontSize = 22.ssp)
            }
        }
    }
}

@Composable
fun AnswerState(
    userAnswer: String,
    modelResult: ClassificationResult,
    onNext: () -> Unit,
    correctClassification: String?
) {
    val isUserCorrect = userAnswer == correctClassification
    val isModelCorrect = modelResult.label == correctClassification
    val cardBackgroundColor = if (isUserCorrect) {
        if (isSystemInDarkTheme()) CorrectAnswerBackgroundDark else CorrectAnswerBackgroundLight
    } else {
        if (isSystemInDarkTheme()) WrongAnswerBackgroundDark else WrongAnswerBackgroundLight
    }
    val cardColors = CardDefaults.cardColors(containerColor = cardBackgroundColor)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
        ) {
            correctClassification?.let {
                Text(
                    text = stringResource(Res.string.correct_classification, it),
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.padding(16.dp),
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        val darkenFactor = 0.2f
        val darkerCardBackgroundColor = lerp(cardBackgroundColor, Color.Black, darkenFactor)
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = cardColors,
            border = BorderStroke(2.dp, darkerCardBackgroundColor)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = stringResource(Res.string.your_answer, userAnswer),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    // Simple formatting without Locale
                    val score = ((modelResult.score * 100).toInt() / 100.0).toString()
                    Text(
                        text = stringResource(Res.string.model_answer, modelResult.label, score),
                        style = MaterialTheme.typography.titleLarge
                    )
                    val iconVector = if (isModelCorrect) Icons.Filled.Check else Icons.Filled.Close
                    Icon(imageVector = iconVector, contentDescription = null)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        val messageTextId: StringResource
        val textColor: Color

        when {
            isUserCorrect -> {
                messageTextId = Res.string.correct_answer_user
                textColor =
                    if (isSystemInDarkTheme()) CorrectAnswerTextDark else CorrectAnswerTextLight
            }

            else -> {
                messageTextId = Res.string.wrong_answer
                textColor = if (isSystemInDarkTheme()) WrongAnswerTextDark else WrongAnswerTextLight
            }
        }

        Text(
            text = stringResource(messageTextId),
            color = textColor,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedButton(
            onClick = onNext, modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        ) {
            Text(stringResource(Res.string.next), fontSize = 22.ssp)
            Spacer(modifier = Modifier.height(4.dp))
            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null)
        }
    }
}

@Preview
@Composable
private fun QuestionStatePreview() {
    val categories = listOf(
        Res.string.world,
        Res.string.sports,
        Res.string.business,
        Res.string.sci_tech
    )
    QuestionState(categories = categories, onAnswer = {})
}

@Preview
@Composable
private fun AnswerStatePreview() {
    val modelResult = ClassificationResult(label = "Sports", score = 0.95)
    AnswerState(
        userAnswer = "Sports",
        modelResult = modelResult,
        onNext = {},
        correctClassification = "Sports"
    )
}
