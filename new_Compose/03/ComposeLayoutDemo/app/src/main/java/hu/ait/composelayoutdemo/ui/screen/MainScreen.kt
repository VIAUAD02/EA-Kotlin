package hu.ait.composelayoutdemo.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import hu.ait.composelayoutdemo.R

@Composable
fun MainScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        //RowDemo()
        //RowMovieDemo()
        //ColumnWeightDemo()
        //ColumnDemo()
        //ScrollColumnDemo()
        //Movie()
        BoxDemo()
    }
}

@Composable
fun RowDemo() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Forrest Gump")
        Text(text = "1994")
        Text(text = "(Author)")
    }
}


@Composable
fun RowMovieDemo() {
    Row(
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.gump),
            contentDescription = "Forrest Gump",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
        )
        Column {
            Text(text = "Forrest Gump")
            Text(text = "1994")
        }
    }
}

@Composable
fun ColumnWeightDemo() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .border(width = 5.dp, color = Color.Red),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(text = "Forrest Gump",
            modifier = Modifier
                .weight(1.0f)
                .fillMaxWidth()
                .background(Color.Blue))
        Text(text = "1994",
            modifier = Modifier
                .weight(1.0f)
                .fillMaxWidth()
                .background(Color.Yellow))
        Text(text = "Movie",
            modifier = Modifier
                .weight(1.0f)
                .fillMaxWidth()
                .background(Color.Blue))
    }
}

@Composable
fun ColumnDemo() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .border(width = 1.dp, color = Color.Blue),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Forrest Gump")
        Text(text = "1994")
        Text(text = "Movie")
    }
}

@Composable
private fun ScrollColumnDemo() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        repeat(100) {
            Text("Item $it", modifier = Modifier.padding(2.dp))
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Movie() {
    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .clickable(onClick = {})
    ) {
        RowMovieDemo()
        Spacer(Modifier.size(10.dp))
        Card(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8. dp,
                pressedElevation = 2. dp,
                focusedElevation = 4. dp
            )
        ) {
            Image(
                painter = painterResource(id = R.drawable.gump_run),
                contentDescription = "Forrest Gump",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}




@Composable
fun BoxDemo() {
    Box(
        modifier = Modifier
            .size(400.dp)
            .border(width = 2.dp, color = Color.Magenta)
    ) {
        Image(
            painter = painterResource(id = R.drawable.gump),
            contentDescription = "Forrest Gump",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
        )


        Text(
            text = "TopStart",
            modifier = Modifier.align(Alignment.TopStart)
        )
        Text(
            text = "TopCenter",
            modifier = Modifier.align(Alignment.TopCenter)
        )
        Text(
            text = "TopEnd",
            modifier = Modifier.align(Alignment.TopEnd)
        )

        Text(
            text = "CenterStart",
            modifier = Modifier.align(Alignment.CenterStart)
        )
        Text(
            text = "CenterEnd",
            modifier = Modifier.align(Alignment.CenterEnd)
        )
        Text(
            text = "Center",
            modifier = Modifier.align(Alignment.Center)
        )

        Text(
            text = "BottomStart",
            modifier = Modifier.align(Alignment.BottomStart)
        )
        Text(
            text = "BottomEnd",
            modifier = Modifier.align(Alignment.BottomEnd)
        )
        Text(
            text = "BottomCenter",
            modifier = Modifier.align(Alignment.BottomCenter)
        )

    }
}