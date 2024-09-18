package hu.ait.toolbardemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_help) {
            Toast.makeText(this, "Help", Toast.LENGTH_LONG).show()
        }
        else if (item.itemId == R.id.action_about) {
            Toast.makeText(this, "About", Toast.LENGTH_LONG).show()
        }
        else if (item.itemId == R.id.action_start) {
            Toast.makeText(this, "Start", Toast.LENGTH_LONG).show()
        }
        if (item.itemId == R.id.action_stop) {
            Toast.makeText(this, "Stop", Toast.LENGTH_LONG).show()
        }

        return true
    }
}