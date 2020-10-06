package hu.bme.aut.animationdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pushAnim = AnimationUtils.loadAnimation(this, R.anim.push_anim)

        btnAnim.setOnClickListener {
            btnAnim.startAnimation(pushAnim)
        }
    }
}