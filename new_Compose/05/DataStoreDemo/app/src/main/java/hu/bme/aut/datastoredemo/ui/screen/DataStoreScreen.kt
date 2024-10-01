package hu.bme.aut.datastoredemo.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import hu.bme.aut.datastoredemo.datastore.MySettings
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataStoreScreen(store: MySettings) {
    var emailValue by remember {
        mutableStateOf("")
    }

    val email = store.getEmail.collectAsState(initial = "")

    Column() {
        Text(text = email.value)

        OutlinedTextField(value = emailValue, onValueChange = {
            emailValue = it
        })

        Button(
            onClick = {
                CoroutineScope(Dispatchers.IO).launch {
                    store.saveEmail(emailValue)
                }
            }
        ) {
            Text(text = "Update email")
        }
    }
}
