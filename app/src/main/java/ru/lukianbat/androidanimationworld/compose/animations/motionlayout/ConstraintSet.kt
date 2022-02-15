package ru.lukianbat.androidanimationworld.compose.animations.motionlayout

import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension

internal fun startConstraintSet() = ConstraintSet {
    val podlodkaImage = createRefFor("podlodkaImage")
    val title = createRefFor("title")

    constrain(podlodkaImage) {
        width = Dimension.fillToConstraints
        start.linkTo(parent.start)
        end.linkTo(parent.end)
        top.linkTo(parent.top)
    }

    constrain(title) {
        start.linkTo(parent.start, 16.dp)
        top.linkTo(podlodkaImage.bottom, 16.dp)
    }
}

internal fun endConstraintSet() = ConstraintSet {
    val podlodkaImage = createRefFor("podlodkaImage")
    val title = createRefFor("title")

    constrain(podlodkaImage) {
        width = Dimension.fillToConstraints
        height = Dimension.value(56.dp)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
        top.linkTo(parent.top)
    }

    constrain(title) {
        start.linkTo(parent.start)
        top.linkTo(parent.top, 8.dp)
        end.linkTo(parent.end)
        bottom.linkTo(podlodkaImage.bottom)
    }
}