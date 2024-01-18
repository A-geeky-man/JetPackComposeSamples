package net.jetpackcompose.composetext.activities // ktlint-disable filename

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import net.jetpackcompose.composetext.R

class AnimationSamplesActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AnimationRootView()
        }
    }

    enum class SelectedAnimation {
        AnimationHomePage,
        ShowAnimateDpAsState,
        ColorAsStateAnimation,
        AnimateAsFloat,
        EnterExitAnimation,
        InfiniteAnimation,
    }

    @ExperimentalAnimationApi
    @Composable
    private fun AnimationRootView() {
        var selectedPage by remember { mutableStateOf(SelectedAnimation.AnimationHomePage) }
        val context = LocalContext.current
        BackHandler() {
            if (selectedPage == SelectedAnimation.AnimationHomePage) {
                (context as Activity).finish()
            } else {
                selectedPage = SelectedAnimation.AnimationHomePage
            }
        }
        Scaffold(topBar = {
            TopAppBar(title = { Text(text = selectedPage.name) }, navigationIcon = {
                IconButton(onClick = {
                    // back press
                    if (selectedPage == SelectedAnimation.AnimationHomePage) {
                        (context as Activity).finish()
                    } else {
                        selectedPage = SelectedAnimation.AnimationHomePage
                    }
                }) {
                    Icon(Icons.Filled.ArrowBack, "backIcon")
                }
            })
        }) {
            // main content list of buttons
            if (selectedPage == SelectedAnimation.AnimationHomePage) {
                Column(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    CustomButton(label = "animateDpAsState") {
                        selectedPage = SelectedAnimation.ShowAnimateDpAsState
                    }

                    CustomButton(label = "ColorAsState") {
                        selectedPage = SelectedAnimation.ColorAsStateAnimation
                    }

                    CustomButton(label = "animateFloatAsState (Rotate)") {
                        selectedPage = SelectedAnimation.AnimateAsFloat
                    }

                    CustomButton(label = "Infinite Animation") {
                        selectedPage = SelectedAnimation.InfiniteAnimation
                    }

                    CustomButton(label = "Enter Exit Animation") {
                        selectedPage = SelectedAnimation.EnterExitAnimation
                    }
                }
            }

            // sub content -> show selected
            else {
                Column(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    when (selectedPage) {
                        SelectedAnimation.ShowAnimateDpAsState -> {
                            AnimateDpAsState()
                        }

                        SelectedAnimation.ColorAsStateAnimation -> {
                            AnimateColorAsState()
                        }

                        SelectedAnimation.AnimateAsFloat -> {
                            AnimateAsFloatContent()
                        }

                        SelectedAnimation.InfiniteAnimation -> {
                            InfiniteAnimation()
                        }

                        SelectedAnimation.EnterExitAnimation -> {
                            AnimatedVisibilityEnterAndExit() // Experimental Api
                        }

                        else -> AnimateDpAsState()
                    }
                }
            }
        }
    }

    @Composable
    private fun CustomButton(label: String, onClick: () -> Unit) {
        Button(
            onClick = onClick,
            modifier = Modifier
                .padding(top = 15.dp)
                .fillMaxWidth(0.8f),
        ) {
            Text(text = label)
        }
    }

    @Composable
    private fun AnimateDpAsState() {
        val isExpanded = remember { mutableStateOf(false) }
        val animatedSizeDp: Dp by animateDpAsState(
            targetValue = if (isExpanded.value) 350.dp else 100.dp,
            animationSpec = tween(
                durationMillis = 2000,
                delayMillis = 100,
            ),
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CircleImage(imageSize = animatedSizeDp)
            Button(
                onClick = { isExpanded.value = !isExpanded.value },
                modifier = Modifier
                    .padding(top = 50.dp)
                    .width(300.dp),
            ) {
                Text(text = "animatedDpAsState")
            }
        }
    }

    @Composable
    private fun AnimateColorAsState() {
        var isColorChanged by remember { mutableStateOf(false) }
        val backgroundColor by animateColorAsState(
            targetValue = if (isColorChanged) Color.Blue else Color.Green,
            animationSpec = tween(
                durationMillis = 2000,
                delayMillis = 100,
            ),
        )
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.8f)
                    .background(backgroundColor),
            )
            Button(
                onClick = { isColorChanged = !isColorChanged },
                modifier = Modifier
                    .padding(top = 10.dp),
            ) {
                Text(text = "Switch Color")
            }
        }
    }

    @Composable
    private fun AnimateAsFloatContent() {
        var isRotated by remember { mutableStateOf(false) }
        val rotationAngle by animateFloatAsState(
            targetValue = if (isRotated) 360F else 0f,
            animationSpec = tween(durationMillis = 2500),
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = R.drawable.fan),
                contentDescription = "fan",
                modifier = Modifier
                    .rotate(rotationAngle)
                    .size(150.dp),
            )
            Button(
                onClick = { isRotated = !isRotated },
                modifier = Modifier
                    .padding(top = 50.dp)
                    .width(200.dp),
            ) {
                Text(text = "Rotate Fan")
            }
        }
    }

    @ExperimentalAnimationApi
    @Composable
    private fun AnimatedVisibilityEnterAndExit() {
        var isVisible by remember { mutableStateOf(false) }
        Column {
            AnimatedVisibility(
                visible = isVisible,
                enter = expandVertically(animationSpec = tween(durationMillis = 2000)),
                exit = shrinkOut(animationSpec = tween(durationMillis = 1000)),
            ) {
                CircleImage(imageSize = 300.dp)
            }
            Button(
                onClick = { isVisible = !isVisible },
                modifier = Modifier
                    .padding(top = 50.dp)
                    .width(300.dp),
            ) {
                Text(text = "AnimatedVisibility")
            }
        }
    }

    @Composable
    fun InfiniteAnimation() {
        val infiniteTransition = rememberInfiniteTransition()
        val imageSize by infiniteTransition.animateFloat(
            initialValue = 100F,
            targetValue = 250F,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 800,
                    delayMillis = 100,
                    easing = FastOutLinearInEasing,
                ),
            ),
        )
        Image(
            painter = painterResource(id = R.drawable.heart),
            contentDescription = "heart",
            modifier = Modifier
                .size(imageSize.dp),
        )
    }

    @Composable
    fun CircleImage(imageSize: Dp) {
        Image(
            painter = painterResource(R.drawable.orange),
            contentDescription = "Circle Image",
            contentScale = ContentScale.Crop, // crop the image if it's not a square
            modifier = Modifier
                .size(imageSize)
                .clip(CircleShape) // clip to the circle shape
                .border(5.dp, Color.Gray, CircleShape), // add a border (optional)
        )
    }
}
