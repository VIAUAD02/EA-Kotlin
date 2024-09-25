package hu.ait.composesizingdemo.ui.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun MainScreen(modifier: Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(Color.Green)
            .size(300.dp)
            //.fillMaxSize()
            //.fillMaxWidth(0.3f)
            //.aspectRatio(1.0f)
    ) {
                Text(text = "Hello BME")
                Text(text = "Hello BME")
                Text(text = "Hello 2BME")
                Text(text = "Hello BME")
                Text(text = "Hello BME")

        }

    }
}