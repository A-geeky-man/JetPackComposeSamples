package net.jetpackcompose.composetext.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import net.jetpackcompose.composetext.R
import net.jetpackcompose.composetext.ui.theme.MyAppTheme
import net.jetpackcompose.composetext.ui.theme.Purple500

class ActivityModifiers : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    ModifierSample()
                }
            }
        }
    }
}

@Composable
fun ModifierSample() {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        BackgroundColor()
        // WidthAndHeightModifier()
//        TextWidthPadding()
//        SizeModifier()
        // AlphaModifier()
//         FillWidthModifier()
//         FillHeightModifier()
        // FillSizeModifier()
        // RotateModifier()
        // WeightModifier()
        // BorderModifier()
        //   BorderWithShape()
//        ClipModifier()
//        IconFunction()
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ModifierPreview() {
    MyAppTheme {
        ModifierSample()
    }
}

@Composable
fun BackgroundColor() {
    Text(
        text = stringResource(id = R.string.text_with_size),
        color = Color.White,
        modifier = Modifier
            .background(Purple500)
            .padding(Dp(10f)),
    )
}

@Composable
fun TextWidthPadding() {
    Text(
        text = "Padding and margin!",
        modifier = Modifier
            .padding(all = 64.dp)
            .background(color = Color.Green)
            .padding(all = 32.dp),
    )
}

@Composable
fun SizeModifier() {
    Text(
        text = "Text with Size",
        color = Color.Black,
        modifier = Modifier
            .background(color = Color.Green)
            .size(64.dp),
    )
}

@Composable
fun WidthAndHeightModifier() {
    Text(
        text = "Width and Height",
        color = Color.White,
        modifier = Modifier
            .background(Color.Blue)
            .width(200.dp)
            .height(300.dp),
    )
}

@Composable
fun FillWidthModifier() {
    Text(
        text = "Text Width Match Parent",
        color = Color.White,
        modifier = Modifier
            .background(Color.Gray)
            .padding(Dp(10f))
            .fillMaxWidth()
            .padding(Dp(10f)),
    )
}

@Composable
fun FillHeightModifier() {
    Text(
        text = " Text with 75% Height ",
        color = Color.White,
        modifier = Modifier
            .background(Color.Green)
            .fillMaxHeight(0.75f),
    )
}

@Composable
fun FillSizeModifier() {
    Text(
        text = " Text with Fill Size (Height and Width) ",
        color = Color.White,
        modifier = Modifier
            .background(Color.Magenta)
            .fillMaxSize(),
    )
}

@Composable
fun AlphaModifier() {
    Box(
        Modifier
            .size(250.dp)
            .alpha(0.5f)
            .background(Color.Red),
    )
}

@Composable
fun WeightModifier() {
    Row() {
        Column(
            Modifier
                .weight(1f)
                .background(Color.Red),
        ) {
            Text(text = "Weight = 1", color = Color.White)
        }
        Column(
            Modifier
                .weight(1f)
                .background(Color.Blue),
        ) {
            Text(text = "Weight = 1", color = Color.White)
        }
        Column(
            Modifier
                .weight(2f)
                .background(Color.Green),
        ) {
            Text(text = "Weight = 2")
        }
    }
}

@Composable
fun RotateModifier() {
    Box(
        Modifier
            .rotate(45f)
            .size(250.dp)
            .background(Color.Gray),
    )
}

@Composable
fun BorderModifier() {
    Text(
        text = "Text with Red Border",
        modifier = Modifier
            .padding(10.dp)
            .background(Color.Yellow)
            .border(2.dp, Color.Red)
            .padding(10.dp),
    )
}

@Composable
fun BorderWithShape() {
    Text(
        text = "Text with round border",

        modifier = Modifier
            .padding(10.dp)
            .border(2.dp, SolidColor(Color.Green), RoundedCornerShape(20.dp))
            .padding(10.dp),
    )
}

@Composable
fun ClipModifier() {
    Text(
        text = "Text with Clipped background",
        color = Color.White,
        modifier = Modifier
            .padding(Dp(10f))
            .clip(RoundedCornerShape(25.dp))
            .background(Color.Blue)
            .padding(Dp(15f)),
    )
}

@Composable
fun ScaleModifier() {
    Box(
        Modifier
            .scale(scaleX = 2f, scaleY = 3f)
            .size(200.dp, 200.dp),
    )
}

@Composable
fun IconFunction() {
    Icon(
        painter = painterResource(id = R.drawable.ic_clock),
        contentDescription = "Clock Icon",
        modifier = Modifier.size(100.dp),
    )
}
