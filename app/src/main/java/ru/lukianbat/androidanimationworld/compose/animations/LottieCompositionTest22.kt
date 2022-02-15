package ru.lukianbat.androidanimationworld.compose.animations

import androidx.compose.animation.core.ExperimentalTransitionApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import ru.lukianbat.androidanimationworld.R

@Composable
fun LottieCompositionTest22() {
    // TODO 22 запуск Lottie анимаций
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.dog_anim))
    LottieAnimation(
        composition = composition,
        isPlaying = true,
        iterations = 20,
        modifier = Modifier
            .size(500.dp)
            .clickable {}
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
        LottieCompositionTest22()
    }
}