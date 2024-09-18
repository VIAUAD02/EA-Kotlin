package hu.bme.aut.kotlindemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var myShip = Ship("Discovery", 10)
        myShip.name
    }
}

class Ship(var name: String){

    init {
        Log.d("TAG_KOT", "primary const body")
    }

    constructor(name: String, speed: Int)  : this(name) {
        Log.d("TAG_KOT", "SEC const body")
    }

}