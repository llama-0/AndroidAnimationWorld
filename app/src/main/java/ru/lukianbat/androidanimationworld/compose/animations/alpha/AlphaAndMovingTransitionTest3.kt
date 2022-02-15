package ru.lukianbat.androidanimationworld.compose.animations.alpha

import androidx.compose.animation.core.ExperimentalTransitionApi
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import ru.lukianbat.androidanimationworld.R

@Composable
fun AlphaAndMovingTransitionTest3() {
    // TODO 3 Запуск анимаций alpha и перемещения c помощью объекта Transition

    var startAnimation by remember { mutableStateOf(false) }
    val transition = updateTransition(startAnimation, label = "")

    val yOffset by transition.animateFloat(
        transitionSpec = { tween(durationMillis = 3000) },
        label = "yOffsetAnimation"
    ) { if (it) 300F else 0F }

    val alpha by transition.animateFloat(
        transitionSpec = { tween(durationMillis = 3000) },
        label = "alphaAnimation"
    ) { if (it) 1F else 0F }

    Image(
        painter = painterResource(R.drawable.ic_podlodka),
        contentDescription = "firstIcon",
        modifier = Modifier
            .size(100F.dp, 100F.dp)
            .offset(y = yOffset.dp)
            .alpha(alpha)
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
        AlphaAndMovingTransitionTest3()
    }
}