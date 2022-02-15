package ru.lukianbat.androidanimationworld.compose.animations.transition

import androidx.compose.animation.core.ExperimentalTransitionApi
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.createChildTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import kotlinx.coroutines.delay
import ru.lukianbat.androidanimationworld.R

@ExperimentalTransitionApi
@Composable
fun CreateChildTransitionTest5() {
    // TODO 5 Декомпозиция составных анимаций с помощью createChildTransition

    var parentState by remember { mutableStateOf(ParentState.InitialState) }
    val parentTransition = updateTransition(parentState, label = "")

    val firstAnimationTransition = parentTransition.createChildTransition {
        it != ParentState.InitialState
    }
    val secondAnimationTransition = parentTransition.createChildTransition {
        it == ParentState.SecondParentState
    }

    val alpha by firstAnimationTransition.animateFloat(
        transitionSpec = { tween(durationMillis = 3000) },
        label = ""
    ) { if (it) 1F else 0F }

    val yOffset by secondAnimationTransition.animateFloat(
        transitionSpec = { tween(durationMillis = 3000) },
        label = ""
    ) { if (it) 400F else 100F }

    Image(
        painter = painterResource(R.drawable.ic_podlodka),
        contentDescription = "firstIcon",
        modifier = Modifier
            .size(100F.dp, 100F.dp)
            .offset(y = yOffset.dp)
            .alpha(alpha)
    )

    LaunchedEffect(true) {
        parentState = ParentState.FirstParentState
        delay(3000)
        parentState = ParentState.SecondParentState
    }
}

enum class ParentState {
    InitialState,
    FirstParentState,
    SecondParentState
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
        CreateChildTransitionTest5()
    }
}