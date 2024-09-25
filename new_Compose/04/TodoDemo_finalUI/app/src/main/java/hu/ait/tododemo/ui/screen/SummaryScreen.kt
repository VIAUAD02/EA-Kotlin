package hu.ait.tododemo.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun TodoSummaryScreen(
    modifier: Modifier = Modifier,
    numalltodo: Int,
    numimportanttodo: Int,
) {
    Column(modifier = modifier.fillMaxSize()
        .padding(start = 40.dp, end = 40.dp), verticalArrangement = Arrangement.Center) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Normal: ${numalltodo - numimportanttodo}",
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                textAlign = TextAlign.Left
            )
            Text(
                text = "Important: $numimportanttodo",
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                textAlign = TextAlign.Right
            )
        }
        Image(
            painter = painterResource(
                hu.ait.tododemo.R.drawable.bracket
            ),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth,
        )

        Text(
            text = "All: $numalltodo",
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}
