package com.example.zingo

import android.icu.text.Transliterator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.zingo.model.FoodItem
import com.bumptech.glide.Glide
import org.w3c.dom.Text

class MenuCardAdapter(private val itemList : List<FoodItem>) : RecyclerView.Adapter<MenuCardAdapter.MenuItemViewHolder>()
{
    class MenuItemViewHolder(view: View) : RecyclerView.ViewHolder(view)
    {
        val img: ImageView = view.findViewById(R.id.itemImage)
        val name: TextView = view.findViewById(R.id.itemName)
        val desc: TextView = view.findViewById(R.id.itemDesc)
        val price: TextView = view.findViewById(R.id.itemPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        return MenuItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: MenuItemViewHolder, position: Int) {
        val item = itemList[position]
        holder.name.text = item.name
        holder.desc.text = item.desc
        holder.price.text = item.price

        Glide.with(holder.itemView.context).load(item.imgUrl).into(holder.img)
    }

    override fun getItemCount(): Int = itemList.size
}