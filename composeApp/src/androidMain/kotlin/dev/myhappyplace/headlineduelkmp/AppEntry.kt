package dev.myhappyplace.headlineduelkmp

import androidx.compose.runtime.Composable
import dev.myhappyplace.headlineduelkmp.ui.viewmodel.HeadlineViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
actual fun AppEntry() {
    val viewModel = koinViewModel<HeadlineViewModel>()
    MainAppView(viewModel)
}
