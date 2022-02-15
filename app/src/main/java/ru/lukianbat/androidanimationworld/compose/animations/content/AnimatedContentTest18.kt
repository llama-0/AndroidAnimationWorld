package ru.lukianbat.androidanimationworld.compose.animations.content

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.EnterExitState
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.ExperimentalTransitionApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ExperimentalMotionApi

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedContentTest18() {
    // TODO 18 Анимация изменения контента (увеличение числа) с помощью AnimatedContent
    var count by remember { mutableStateOf(0) }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AnimatedContent(
            targetState = count,
            transitionSpec = {
                if (targetState > initialState) {
                    slideInVertically { height -> height } with
                            slideOutVertically { height -> -height }
                } else {
                    slideInVertically { height -> -height } with
                            slideOutVertically { height -> height }
                }
            }
        ) { targetCount ->
            val background by transition.animateColor(
                label = "",
                transitionSpec = { tween(2000) }
            ) { state ->
                if (state == EnterExitState.Visible) Color.Green else Color.Red
            }
            // Важно использовать targetCount
            Text(
                fontSize = 48.sp,
                color = background,
                text = "$targetCount"
            )
        }

        Button(
            onClick = { count++ },
            Modifier.offset(y = 28.dp)
        ) {
            Text(
                text = "Plus",
                fontSize = 21.sp,
            )
        }
        Button(
            onClick = { count-- },
            Modifier.offset(y = 28.dp)
        ) {
            Text(
                text = "Minus",
                fontSize = 21.sp,
            )
        }
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
        AnimatedContentTest18()
    }
}