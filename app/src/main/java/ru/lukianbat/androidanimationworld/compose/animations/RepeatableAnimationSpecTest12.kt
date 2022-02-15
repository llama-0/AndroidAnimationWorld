package ru.lukianbat.androidanimationworld.compose.animations

import androidx.compose.animation.core.ExperimentalTransitionApi
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.repeatable
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import ru.lukianbat.androidanimationworld.R

@Composable
fun RepeatableAnimationSpecTest12() {
    // TODO 12 Анимация перемещения с 5-кратным повтором
    var startAnimation by remember { mutableStateOf(false) }
    val yOffsetAnimatable: Float by animateFloatAsState(
        targetValue = if (startAnimation) 500f else 0f,
        animationSpec = repeatable(
            iterations = 5,
            animation = tween(durationMillis = 500),
            repeatMode = RepeatMode.Reverse
        )
    )

    Image(
        painter = painterResource(R.drawable.ic_podlodka),
        contentDescription = null,
        modifier = Modifier
            .size(100F.dp, 100F.dp)
            .offset(y = yOffsetAnimatable.dp)
    )

    Button(onClick = {
        startAnimation = startAnimation.not()
    }, modifier = Modifier.offset(y = 600.dp)) {
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
        RepeatableAnimationSpecTest12()
    }
}