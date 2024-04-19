package ui.screens.home.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import domain.RequestState

@Composable
fun <T> RequestState<T>.DisplayResult(
    onIdle: (@Composable () -> Unit)? = null,
    onLoading: @Composable () -> Unit,
    onSuccess: @Composable (T) -> Unit,
    onError: @Composable (String) -> Unit,
    transitionSpec: AnimatedContentTransitionScope<*>.() -> ContentTransform = {
        fadeIn(tween(durationMillis = 300)) togetherWith
                fadeOut(tween(durationMillis = 300))
    }
) {
    AnimatedContent(
        targetState = this,
        transitionSpec = transitionSpec,
        label = "Animated State"
    ) { state ->
        when (state) {
            is RequestState.Idle -> {
                onIdle?.invoke()
            }

            is RequestState.Loading -> {
                onLoading()
            }

            is RequestState.Success -> {
                onSuccess(state.getSuccessData())
            }

            is RequestState.Error -> {
                onError(state.getErrorMessage())
            }
        }
    }
}