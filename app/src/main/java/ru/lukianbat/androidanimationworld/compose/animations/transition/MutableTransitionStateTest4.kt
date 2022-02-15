package ru.lukianbat.androidanimationworld.compose.animations.transition

import androidx.compose.animation.core.ExperimentalTransitionApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import ru.lukianbat.androidanimationworld.R

@Composable
fun MutableTransitionStateTest4() {
    // TODO 4 Запуск анимации перемещения во время компоновки с помощью MutableTransitionState

    var currentState = remember { MutableTransitionState(false) }
    currentState.targetState = true
    val transition = updateTransition(currentState, label = "")

    val yOffset by transition.animateFloat(
        transitionSpec = { tween(durationMillis = 1500) },
        label = ""
    ) { if (it) 300F else 0F }

    Image(
        painter = painterResource(R.drawable.ic_podlodka),
        contentDescription = "firstIcon",
        modifier = Modifier
            .size(100F.dp, 100F.dp)
            .offset(y = yOffset.dp)
    )
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
        MutableTransitionStateTest4()
    }
}