package ru.lukianbat.androidanimationworld.compose.animations.visibility

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.ExperimentalTransitionApi
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
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
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import ru.lukianbat.androidanimationworld.R

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedVisibilityTest14() {
    // TODO 14 Анимация появления контента с помощью AnimatedVisibility

    var visible by remember { mutableStateOf(true) }
    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically(),
        exit = slideOutVertically()
    ) {

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(R.drawable.ic_podlodka),
                contentDescription = null,
                modifier = Modifier
                    .size(200F.dp, 200F.dp)
            )
            Text(
                "Hello Podlodka!!!",
                fontSize = 48.sp,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .wrapContentWidth()
            )
        }
    }

    Button(
        onClick = {
            visible = visible.not()
        },
        modifier = Modifier.offset(y = 28.dp),
    ) {
        Text(
            "Запустить анимацию",
            fontSize = 21.sp,
            modifier = Modifier
                .wrapContentWidth()
        )
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
        AnimatedVisibilityTest14()
    }
}