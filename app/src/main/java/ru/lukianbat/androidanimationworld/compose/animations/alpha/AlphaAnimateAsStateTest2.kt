package ru.lukianbat.androidanimationworld.compose.animations.alpha

import androidx.compose.animation.core.ExperimentalTransitionApi
import androidx.compose.animation.core.animateFloatAsState
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import ru.lukianbat.androidanimationworld.R

@Composable
fun AlphaAnimateAsStateTest2() {
    // TODO 2 Запуск анимации изменения alpha с помощью функции animateFloatAsState
    var imageEnabled by remember { mutableStateOf(false) }
    val alpha: Float by animateFloatAsState(
        targetValue = if (imageEnabled) 1f else 0f,
        animationSpec = tween(1500)
    )
    Image(
        painter = painterResource(R.drawable.ic_podlodka),
        contentDescription = null,
        modifier = Modifier
            .size(100F.dp, 100F.dp)
            .alpha(alpha)
    )

    Button(
        onClick = {
            imageEnabled = imageEnabled.not()
        },
        Modifier.offset(y = 300.dp)
    ) {
        Text("Change imageEnabled state")
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
        AlphaAnimateAsStateTest2()
    }
}