package io.github.adibfara.instagram.animation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.adibfara.instagram.animation.model.AnimationParams
import io.github.adibfara.instagram.animation.ui.theme.InstagramAnimationTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InstagramAnimationTheme {
                // A surface container using the 'background' color from the theme
                Column(
                    Modifier
                        .fillMaxSize()
                        .background(Color.Black)
                ) {

                    Image(
                        painterResource(id = R.drawable.logo),
                        contentDescription = null,
                        modifier = Modifier
                            .width(140.dp)
                            .padding(16.dp)
                    )
                    BoxWithConstraints(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(16.dp),
                    ) {
                        val strokeSize = 14.dp

                        Image(
                            painter = painterResource(id = R.drawable.cover),
                            contentDescription = null,
                            modifier = Modifier
                                .size(maxWidth, maxWidth)
                                .padding(strokeSize + 8.dp)
                                .clip(CircleShape),
                            alignment = Alignment.TopStart
                        )

                        InstagramCircle(strokeSize)
                        Text(
                            "@AdibCodes", fontSize = 42.sp, color = Color.White,
                            modifier = Modifier
                                .padding(top = maxWidth + 32.dp)
                                .align(Alignment.TopCenter)
                        )

                    }
                    Text(
                        """linkedin.com/in/adibfara
                            |Telegram & YouTube Channels: @AdibCodes
                        """.trimMargin(),
                        color = Color.White,
                        modifier = Modifier.padding(16.dp),
                        fontSize = 12.sp
                    )
                }
            }
        }
    }


}