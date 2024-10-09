package hu.bme.aut.retrofithttpdemos.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import hu.bme.aut.retrofithttpdemos.data.RowerPhotos
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun NasaMarsApiScreen(
    nasaMarsViewModel: NasaMarsViewModel = viewModel()
) {
    var earthDate by rememberSaveable { mutableStateOf("2015-6-3") }
    Column(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(0.8f),
            label = {
                Text(text = "Earth date")
            },
            value = earthDate,
            onValueChange = {
                earthDate = it
            },
            singleLine = true,
            leadingIcon = {
                Icon(Icons.Default.DateRange, null)
            }
        )
        Button(onClick = {
            nasaMarsViewModel.getRowerPhotos(earthDate)
        }) {
            Text(text = "Refresh")
        }
        when (nasaMarsViewModel.marsUiState) {
            is MarsUiState.Loading -> CircularProgressIndicator()
            is MarsUiState.Success -> ResultScreen((nasaMarsViewModel.marsUiState as MarsUiState.Success).rowerPhotsResult)
            is MarsUiState.Error -> Text(text = "Error...")
        }
    }

}

@Composable
fun ResultScreen(rowerPhotsResult: RowerPhotos) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(rowerPhotsResult.photos!!) {
            RowerPhotCard(rowerName = it!!.rover!!.name!!,
                earthDate = it!!.earthDate!!,
                photoUrl = it!!.imgSrc!!)
        }
    }
}


@Composable
fun RowerPhotCard (
    rowerName: String,
    earthDate: String,
    photoUrl: String
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {

            Text(
                text = rowerName
            )
            Text(
                text = earthDate
            )

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(photoUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = "Image",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.size(250.dp)
            )
        }
    }
}