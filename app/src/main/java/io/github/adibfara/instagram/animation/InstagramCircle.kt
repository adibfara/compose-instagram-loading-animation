package io.github.adibfara.instagram.animation

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import io.github.adibfara.instagram.animation.model.AnimationParams
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
internal fun InstagramCircle(strokeSize: Dp, modifier: Modifier = Modifier) {
    val animationParams = remember { AnimationParams() }

    val animatables = remember {
        Array(animationParams.totalArcs) {
            Animatable(1f, 0f)
        }
    }

    val rotation = remember {
        Animatable(0f, 0f)
    }

    val coroutineScope = rememberCoroutineScope()
    Canvas(
        modifier = modifier
            .fillMaxSize()
            .padding(strokeSize / 2)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }) {
                coroutineScope.launch {
                    startAnimation(
                        animatables,
                        animationParams
                    )
                }
                coroutineScope.launch {
                    val rotationAnimationDuration =
                        animationParams.startDuration + animationParams.endDuration + animationParams.waitDelay
                    rotation.animateTo(
                        rotation.value + 3 * animationParams.eachAngle,
                        tween(
                            (2f * rotationAnimationDuration).toInt(),
                            easing = LinearEasing
                        ),

                        )
                }
            }

    ) {
        (0 until animationParams.totalArcs).forEach { angle ->
            val animatable = animatables[angle]
            val animationAmount = animatable.value
            val offset =
                ((1f - animationAmount) / 2f) * animationParams.eachAngle

            val startAngle =
                rotation.value + -90f + offset + (angle * animationParams.eachAngle)
            val offsetAmount = size.width * 0.1f
            drawArc(
                Brush.linearGradient(
                    Pair(0f, animationParams.startColor),
                    Pair(1f, animationParams.endColor),
                    start = Offset(
                        size.width - offsetAmount, offsetAmount
                    ),
                    end = Offset(
                        offsetAmount, size.width - offsetAmount
                    )
                ),
                startAngle,
                animationParams.eachAngle * animationAmount,
                false,
                size = Size(size.width, size.width),
                style = Stroke(
                    strokeSize.toPx() * animationAmount,
                    cap = StrokeCap.Round
                )
            )
        }

    }
}


private fun CoroutineScope.startAnimation(
    animatables: Array<Animatable<Float, AnimationVector1D>>,
    animationParams: AnimationParams

) {

    animatables.forEachIndexed { index, animatable ->
        launch {
            applyInstagramAnimation(
                animatable, index,
                animationParams
            )
        }
    }
}


private suspend fun applyInstagramAnimation(
    animatable: Animatable<Float, AnimationVector1D>,
    index: Int,
    animationParams: AnimationParams
) {
    val startDelayAmount = animationParams.startDelayAmount
    val startDuration = animationParams.startDuration
    val endDuration = animationParams.endDuration
    val waitDelay = animationParams.waitDelay
    delay(index * startDelayAmount)
    animatable.animateTo(
        0f, tween(
            startDuration, easing = LinearEasing
        )
    )
    delay(waitDelay)
    animatable.animateTo(
        1f, tween(
            endDuration, easing = LinearEasing
        )
    )

}
