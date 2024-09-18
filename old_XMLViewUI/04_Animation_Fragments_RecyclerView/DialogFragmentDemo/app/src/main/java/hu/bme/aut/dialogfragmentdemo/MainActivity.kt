package hu.bme.aut.dialogfragmentdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.bme.aut.dialogfragmentdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), QueryFragment.OnQueryFragmentAnswer, SelectFruitFragment.OptionsFragmentInterface {

    private lateinit var binding : ActivityMainBinding

    companion object {
        val KEY_MSG = "KEY_MSG"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDialogMessage.setOnClickListener {
            val queryFragment = QueryFragment()
            queryFragment.isCancelable = false

            val bundle = Bundle()
            bundle.putString(KEY_MSG, "Hello Advanced Android")
            queryFragment.arguments = bundle

            queryFragment.show(supportFragmentManager,
                "QueryFragment")
        }

        binding.btnDialogList.setOnClickListener {
            SelectFruitFragment().show(supportFragmentManager, SelectFruitFragment.TAG)
        }
    }

    override fun onPositiveSelected(text: String) {
        binding.tvData.text = "OK selected $text"
    }

    override fun onNegativeSelected() {
        binding.tvData.text = "NOPE selected"
    }

    override fun onOptionsFragmentResult(fruit: String) {
        binding.tvData.text = fruit
    }
}