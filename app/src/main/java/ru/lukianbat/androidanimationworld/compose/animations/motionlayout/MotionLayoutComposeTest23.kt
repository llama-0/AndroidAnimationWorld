package ru.lukianbat.androidanimationworld.compose.animations.motionlayout

import androidx.compose.animation.core.ExperimentalTransitionApi
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import ru.lukianbat.androidanimationworld.R


@ExperimentalMaterialApi
@ExperimentalMotionApi
@Composable
fun MotionLayoutComposeTest23() {
    // TODO 23 MotionLayout

    val screenHeight = LocalConfiguration.current.screenHeightDp.toFloat()

    val swipingState = rememberSwipeableState(initialValue = SwipingStates.EXPANDED)

    val animateMotionLayoutProgress by animateFloatAsState(
        targetValue = if (swipingState.progress.to == SwipingStates.COLLAPSED) {
            swipingState.progress.fraction
        } else {
            1f - swipingState.progress.fraction
        },
        animationSpec = spring(Spring.DampingRatioHighBouncy)
    )

    MotionLayout(
        start = startConstraintSet(),
        end = endConstraintSet(),
        progress = animateMotionLayoutProgress,
        modifier = Modifier
            .fillMaxWidth()
            .height(screenHeight.dp)
            .swipeable(
                state = swipingState,
                thresholds = { _, _ -> FractionalThreshold(0.5f) },
                orientation = Orientation.Vertical,
                anchors = mapOf(
                    0f to SwipingStates.COLLAPSED,
                    screenHeight to SwipingStates.EXPANDED,
                )
            )
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_podlodka),
            contentDescription = "",
            modifier = Modifier
                .layoutId("podlodkaImage")
                .background(MaterialTheme.colors.primary)
                .alpha(alpha = 1f - animateMotionLayoutProgress),
            contentScale = ContentScale.FillWidth
        )
        Text(
            text = "Hi, podlodka!",
            modifier = Modifier
                .layoutId("title")
                .wrapContentHeight(),
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center
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
        MotionLayoutComposeTest23()
    }
}