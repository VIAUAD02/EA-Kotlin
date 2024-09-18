package hu.bme.aut.shoppinglistrecyclerviewdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.bme.aut.shoppinglistrecyclerviewdemo.adapter.ShoppingAdapter
import hu.bme.aut.shoppinglistrecyclerviewdemo.data.ShoppingItem
import hu.bme.aut.shoppinglistrecyclerviewdemo.databinding.ActivityMainBinding
import hu.bme.aut.shoppinglistrecyclerviewdemo.dialog.ShoppingItemDialog

class MainActivity : AppCompatActivity(), ShoppingItemDialog.ShoppingItemDialogHandler {

    private lateinit var adapter: ShoppingAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ShoppingAdapter(this,
            mutableListOf<ShoppingItem>(ShoppingItem("myDemo",22,"hello", false, 1)))
        binding.recyclerShopping.adapter = adapter

        binding.fab.setOnClickListener { view ->
            ShoppingItemDialog().show(supportFragmentManager, "TAG_SHOP_DIALOG")
        }

        binding.fabDeleteAll.setOnClickListener { view ->
            adapter.deleteAll()
        }
    }

    override fun shoppingItemCreated(item: ShoppingItem) {
        adapter.addItem(item)
    }

    override fun shoppingItemModified(item: ShoppingItem) {
        //..
    }


}