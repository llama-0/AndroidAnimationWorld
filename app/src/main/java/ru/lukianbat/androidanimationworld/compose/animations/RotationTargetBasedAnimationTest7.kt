package ru.lukianbat.androidanimationworld.compose.animations

import androidx.compose.animation.core.ExperimentalTransitionApi
import androidx.compose.animation.core.TargetBasedAnimation
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import ru.lukianbat.androidanimationworld.R

@Composable
fun RotationTargetBasedAnimationTest7() {
    // TODO 7 Анимация вращения с помощью TargetBasedAnimation

    var rotation by remember { mutableStateOf(0F) }
    var startAnimation by remember { mutableStateOf(false) }

    val anim = remember {
        TargetBasedAnimation(
            animationSpec = tween(3000),
            typeConverter = Float.VectorConverter,
            initialValue = 0f,
            targetValue = 360f
        )
    }

    LaunchedEffect(startAnimation) {
        var playTime: Long
        val startTime = withFrameNanos { it }
        do {
            playTime = withFrameNanos { it } - startTime
            rotation = anim.getValueFromNanos(playTime)
        } while (!anim.isFinishedFromNanos(playTime))
    }

    Image(
        painter = painterResource(R.drawable.ic_podlodka),
        contentDescription = "secondIcon",
        modifier = Modifier
            .size(100F.dp, 100F.dp)
            .rotate(rotation)
    )

    Button(
        onClick = {
            startAnimation = startAnimation.not()
        },
        Modifier.offset(y = 300.dp)
    ) {
        Text("Change startAnimation state")
    }
}

@OptIn(
    ExperimentalTransitionApi::class,
    ExperimentalMaterialApi::class,
    ExperimentalMotionApi::class
)
@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RotationTargetBasedAnimationTest7()
    }
}