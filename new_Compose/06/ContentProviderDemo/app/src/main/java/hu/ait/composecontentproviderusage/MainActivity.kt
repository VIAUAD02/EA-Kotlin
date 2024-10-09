package hu.ait.composecontentproviderusage

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.CalendarContract
import android.provider.ContactsContract
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import hu.ait.composecontentproviderusage.ui.theme.ComposeContentProviderUsageTheme
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeContentProviderUsageTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ContentProviderScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ContentProviderScreen() {
    val context = LocalContext.current

    var permissionGranted by remember { mutableStateOf(false) }

    val permissionsState = rememberMultiplePermissionsState(
        permissions = listOf(
            android.Manifest.permission.READ_CONTACTS,
            android.Manifest.permission.WRITE_CALENDAR
        ),
        onPermissionsResult = {
            if (it[android.Manifest.permission.READ_CONTACTS]!! &&
                it[android.Manifest.permission.WRITE_CALENDAR]!!
            ) {
                permissionGranted = true
            }
        }
    )

    LaunchedEffect(true) {
        permissionsState.launchMultiplePermissionRequest()
    }


    Column() {
        if (permissionGranted) {
            Button(onClick = {
                getContacts(context)
            }) {
                Text(text = "Read contacts")
            }

            Button(onClick = {
                insertCalendarEvent(context)
            }) {
                Text(text = "Insert to calendar")
            }
        }
    }
}


@SuppressLint("Range")
private fun getContacts(context: Context) {
    val cursorContacts = context.contentResolver.query(
        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,

        arrayOf(
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER
        ),
        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
                + " LIKE '%Tamás%'",
        //null,
        null,
        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
                + " DESC"
    )

    Toast.makeText(
        context, "" + cursorContacts!!.count,
        Toast.LENGTH_LONG
    ).show();

    while (cursorContacts!!.moveToNext()) {
        val name = cursorContacts.getString(
            cursorContacts.getColumnIndex(
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
            )
        )
        Toast.makeText(context, name, Toast.LENGTH_LONG).show()
    }
}

fun insertCalendarEvent(context: Context) {
    try {
        val values = ContentValues()
        values.put(
            CalendarContract.Events.DTSTART,
            System.currentTimeMillis()
        )
        values.put(
            CalendarContract.Events.DTEND,
            System.currentTimeMillis() + 60000
        )
        values.put(CalendarContract.Events.TITLE, "Demo")
        values.put(
            CalendarContract.Events.DESCRIPTION,
            "This is an event in my calendar :)"
        )
        values.put(CalendarContract.Events.CALENDAR_ID, 1)
        values.put(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault().getID())

        val uri = context.contentResolver.insert(
            CalendarContract.Events.CONTENT_URI, values
        )

        //contentResolver.delete(CalendarContract.Events.CONTENT_URI, CalendarContract.Events._ID+"=599", null)

    } catch (e: SecurityException) {
        e.printStackTrace()
    }
}