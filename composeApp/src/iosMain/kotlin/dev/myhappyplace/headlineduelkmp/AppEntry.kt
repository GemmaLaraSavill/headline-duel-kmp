package dev.myhappyplace.headlineduelkmp

import androidx.compose.runtime.Composable
import dev.myhappyplace.headlineduelkmp.ui.viewmodel.HeadlineViewModel
import org.koin.compose.koinInject

@Composable
actual fun AppEntry() {
    val viewModel: HeadlineViewModel = koinInject()
    MainAppView(viewModel)
}
