package hu.ait.simplecalculatererrorhandlingdemo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import hu.ait.simplecalculatererrorhandlingdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnPlus.setOnClickListener {
            try {
                if (binding.etNumA.text.isNotEmpty()) {

                    val numA = binding.etNumA.text.toString().toInt()
                    val numB = binding.etNumB.text.toString().toInt()
                    val sum = numA + numB
                    binding.tvResult.text = "Result: $sum"
                } else {
                    binding.etNumA.error = "This field can not be empty"
                }
            } catch (e: Exception) {
                binding.etNumA.error = "Error: ${e.message}"
            }
        }



    }
}