package hu.bme.aut.shoppinglistrecyclerviewdemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.shoppinglistrecyclerviewdemo.R
import hu.bme.aut.shoppinglistrecyclerviewdemo.data.ShoppingItem
import hu.bme.aut.shoppinglistrecyclerviewdemo.databinding.ShopItemBinding

class ShoppingAdapter : RecyclerView.Adapter<ShoppingAdapter.ViewHolder> {

    private val items = mutableListOf<ShoppingItem>()
    private val context: Context

    constructor(context: Context, itemsList: List<ShoppingItem>) : super() {
        this.context = context
        items.addAll(itemsList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val shopItemBinding = ShopItemBinding.inflate(
            LayoutInflater.from(context), parent, false)
        return ViewHolder(shopItemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[holder.adapterPosition])

        holder.shopItemBinding.btnDelete.setOnClickListener {
            deleteItem(holder.adapterPosition)
        }
    }

    private fun deleteItem(adapterPosition: Int) {
        items.removeAt(adapterPosition)
        notifyItemRemoved(adapterPosition)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addItem(item: ShoppingItem) {
        items.add(item)
        notifyItemInserted(items.lastIndex)
        //notifyDataSetChanged()
    }

    fun deleteAll() {
        items.clear()
        notifyDataSetChanged()
    }

    inner class ViewHolder(val shopItemBinding: ShopItemBinding): RecyclerView.ViewHolder(shopItemBinding.root) {
        fun bind(shopItem: ShoppingItem) {
            shopItemBinding.tvName.text = shopItem.name
            shopItemBinding.tvPrice.text = shopItem.price.toString()

            when (shopItem.category) {
                0 -> {
                    shopItemBinding.ivItemLogo.setImageResource(
                        R.drawable.food)
                }
                1 -> {
                    shopItemBinding.ivItemLogo.setImageResource(
                        R.drawable.clothes)
                }
                2 -> {
                    shopItemBinding.ivItemLogo.setImageResource(
                        R.drawable.sport)
                }
            }
        }
    }

}