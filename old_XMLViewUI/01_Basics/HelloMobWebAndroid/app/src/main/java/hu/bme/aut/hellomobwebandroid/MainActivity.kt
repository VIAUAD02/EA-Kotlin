package hu.bme.aut.hellomobwebandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDemo.setOnClickListener {
            var timeText = Date(System.currentTimeMillis()).toString()
            tvData.append("BME $timeText")

            Toast.makeText(this, timeText, Toast.LENGTH_LONG).show()
        }
    }
}