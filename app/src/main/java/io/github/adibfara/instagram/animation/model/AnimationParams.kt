package io.github.adibfara.instagram.animation.model

import androidx.compose.ui.graphics.Color

class AnimationParams {
     val totalArcs = 30
     val eachAngle = 360f / totalArcs.toFloat()
     val startDelayAmount = 13L
     val startDuration = 800
     val endDuration = (startDuration * 0.6f).toInt()
     val waitDelay: Long = 200
     val startColor = Color(0xffa52c9d)
     val endColor = Color(0xfff7c269)
 }
