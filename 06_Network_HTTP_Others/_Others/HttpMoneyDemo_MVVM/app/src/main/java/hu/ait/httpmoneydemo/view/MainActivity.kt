package hu.ait.httpmoneydemo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import hu.ait.httpmoneydemo.R
import hu.ait.httpmoneydemo.viewmodel.MoneyViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var moneyViewModel: MoneyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        moneyViewModel = ViewModelProvider(this).get(MoneyViewModel::class.java)
        moneyViewModel.response.observe(this, Observer { moneyResult ->
            tvData.text = moneyResult
        })

        btnGetRates.setOnClickListener {
            moneyViewModel.getRates(etEur.text.toString().toDouble())
        }
    }
}
