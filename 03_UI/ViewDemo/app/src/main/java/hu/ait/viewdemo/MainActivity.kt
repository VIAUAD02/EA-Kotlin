package hu.ait.viewdemo

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val cityNames = arrayOf("New York",
        "New Jersey",
        "New Delhi", "Budapest", "Bukarest")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var cityAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_dropdown_item_1line,
            cityNames
        )
        autoCompleteCities.threshold = 1
        autoCompleteCities.setAdapter(cityAdapter)

        rbtnWhite.setOnClickListener {
            layoutMain.setBackgroundColor(Color.WHITE)
        }
        rbtnGreen.setOnClickListener {
            layoutMain.setBackgroundColor(Color.GREEN)
        }
        rbtnRed.setOnClickListener {
            layoutMain.setBackgroundColor(Color.RED)
        }


        var fruitsAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.fruitsArray,
            android.R.layout.simple_spinner_item
        )
        fruitsAdapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )
        spinnerFruits.adapter = fruitsAdapter
        spinnerFruits.setSelection(2)

    }
}
