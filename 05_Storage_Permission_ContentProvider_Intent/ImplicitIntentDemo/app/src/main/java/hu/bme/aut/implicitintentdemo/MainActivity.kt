package hu.bme.aut.implicitintentdemo

import android.Manifest
import android.app.AlertDialog
import android.app.SearchManager
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import hu.bme.aut.implicitintentdemo.databinding.ActivityMainBinding
import permissions.dispatcher.*

@RuntimePermissions
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    companion object {
        const val REQUEST_CALL_STATE = 1001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val b = intent.extras
        if (b != null && b.containsKey(Intent.EXTRA_STREAM)) {
            val uri = b[Intent.EXTRA_STREAM] as Uri
            binding.ivData.setImageURI(uri)
        }

        binding.btnIntentDial.setOnClickListener {
            intentDialWithPermissionCheck()
        }

        //requestPermission()
    }

    @OnPermissionDenied(Manifest.permission.CALL_PHONE)
    fun onCameraDenied() {
        Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
    }

    @OnNeverAskAgain(Manifest.permission.CALL_PHONE)
    fun onCameraNeverAskAgain() {
        Toast.makeText(this, "Permission never asked again", Toast.LENGTH_SHORT).show()
    }

    @OnShowRationale(Manifest.permission.CALL_PHONE)
    fun showRationaleDialog(
        request: PermissionRequest
    ) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Engedély")
            .setMessage("Szükséges a teszthez")
            .setPositiveButton("Ok") { _, _ ->
                request.proceed()
            }
            .setNegativeButton("Cancel") { _, _ ->
                request.cancel()
            }
        builder.create().show()

    }

    fun intentSearch(v: View) {
        val intentSearch = Intent(Intent.ACTION_WEB_SEARCH)
        intentSearch.putExtra(SearchManager.QUERY, "Balaton")
        startActivity(intentSearch)
    }

    @NeedsPermission(Manifest.permission.CALL_PHONE)
    fun intentDial() {
        val intentDial = Intent(Intent.ACTION_CALL, Uri.parse("tel:+36123456789"))

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            startActivity(intentDial)
        }
    }

    fun intentSend(v: View) {
        val intentSend = Intent(Intent.ACTION_SEND)
        intentSend.type = "text/plain"
        //intentSend.`package` = "com.facebook.katana"
        intentSend.putExtra(Intent.EXTRA_TEXT, "Jee  Tanfolyam!")
        startActivity(intentSend)
        startActivity(Intent.createChooser(intentSend, "Select share app"));
    }

    fun intentWaze(v: View) {
        //String wazeUri = "waze://?favorite=Home&navigate=yes";
        //val wazeUri = "waze://?ll=40.761043, -73.980545&navigate=yes"
        val wazeUri = "waze://?q=BME&navigate=yes"

        val intentTest = Intent(Intent.ACTION_VIEW)
        intentTest.data = Uri.parse(wazeUri)
        startActivity(intentTest)
    }

    fun intentStreetView(v: View) {
        val gmmIntentUri = Uri.parse(
            "google.streetview:cbll=29.9774614,31.1329645&cbp=0,30,0,0,-15"
        )
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }


    /*private fun requestPermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE)
            != PackageManager.PERMISSION_GRANTED
        ) {
            if (shouldShowRequestPermissionRationale(android.Manifest.permission.CALL_PHONE)) {
                showRationaleDialog(
                    "Engedély kérés",
                    "Szükség van rá, mivel ezt teszteljük",
                    android.Manifest.permission.CALL_PHONE,
                    REQUEST_CALL_STATE
                )
            } else {
                requestPermissions(
                    arrayOf(android.Manifest.permission.CALL_PHONE),
                    REQUEST_CALL_STATE
                )
            }
        } else {
            // Permission is already granted
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_CALL_STATE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun showRationaleDialog(
        title: String,
        message: String,
        permission: String,
        requestCode: Int
    ) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle(title)
            .setMessage(message)
            .setPositiveButton("Ok") { _, _ ->
                requestPermissions(arrayOf(permission), requestCode)
            }
        builder.create().show()

    }*/
}