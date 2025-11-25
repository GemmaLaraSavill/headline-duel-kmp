package dev.myhappyplace.headlineduelkmp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import dev.myhappyplace.headlineduelkmp.ui.screen.HeadlineScreen
import dev.myhappyplace.headlineduelkmp.ui.screen.InformationScreen
import dev.myhappyplace.headlineduelkmp.ui.theme.HeadlineDuelTheme
import dev.myhappyplace.headlineduelkmp.ui.viewmodel.HeadlineViewModel

@Composable
fun MainAppView(viewModel: HeadlineViewModel) {
    HeadlineDuelTheme {
        var showInfo by remember { mutableStateOf(false) }
        if (showInfo) {
            InformationScreen(onBack = { showInfo = false })
        } else {
            HeadlineScreen(
                viewModel = viewModel,
                onNavigateToInfo = { showInfo = true }
            )
        }
    }
}
