package hu.bme.aut.layoutinflaterdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.data_row.view.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSave.setOnClickListener {
            if (etTodo.text.isEmpty()) {
                etTodo.error = "This field can not be empty"
            } else {
                saveData()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_start) {
            Toast.makeText(
                this,
                item.getTitle(), Toast.LENGTH_LONG
            ).show()
        } else if (item.itemId == R.id.action_stop) {
            Toast.makeText(
                this,
                item.getTitle(), Toast.LENGTH_LONG
            ).show()
        } else if (item.itemId == R.id.action_add) {
            saveData()
        }


        return super.onOptionsItemSelected(item)
    }


    private fun saveData() {
        var viewTodo = layoutInflater.inflate(R.layout.data_row, null, false)

        viewTodo.tvData.text = etTodo.text.toString()

        layoutMain.addView(viewTodo, 0)
    }
}