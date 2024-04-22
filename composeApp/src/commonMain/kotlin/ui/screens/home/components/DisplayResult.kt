package ui.screens.home.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedVisibility
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
    AnimatedVisibility(
        visible = this is RequestState.Idle,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        onIdle?.invoke()
    }

    AnimatedVisibility(
        visible = this is RequestState.Loading,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        onLoading()
    }

    AnimatedVisibility(
        visible = this is RequestState.Error,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        onError(getErrorMessage())
    }

    AnimatedVisibility(
        visible = this is RequestState.Success,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        onSuccess(getSuccessData())
    }
}