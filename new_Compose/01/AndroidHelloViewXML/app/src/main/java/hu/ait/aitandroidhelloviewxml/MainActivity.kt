package hu.ait.aitandroidhelloviewxml

import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import hu.ait.aitandroidhelloviewxml.databinding.ActivityMainBinding
import java.util.Date

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        //enableEdgeToEdge()

        setContentView(binding.main)

        binding.btnShow.setOnClickListener {

            binding.tvData.text = Date(System.currentTimeMillis()).toString()

            binding.main.setBackgroundColor(Color.RED)
        }

    }

}