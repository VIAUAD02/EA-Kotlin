package hu.bme.aut.animationdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationUtils
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pushAnim = AnimationUtils.loadAnimation(this, R.anim.push_anim)

        pushAnim.setAnimationListener(object: AnimationListener {
            override fun onAnimationStart(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {
                Toast.makeText(this@MainActivity,
                    "VÉGE", Toast.LENGTH_SHORT).show()
            }

            override fun onAnimationRepeat(p0: Animation?) {

            }

        })



        btnAnim.setOnClickListener {
            btnAnim.startAnimation(pushAnim)
        }
    }
}