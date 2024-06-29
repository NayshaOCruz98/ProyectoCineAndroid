package com.example.proyectoandroid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class ConfiteriaItem(val image: Int, val name: String, val price: String, val description: String)

class ConfiteriaAdapter(val items: List<ConfiteriaItem>, private val onQuantityChanged: () -> Unit) : RecyclerView.Adapter<ConfiteriaAdapter.ConfiteriaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConfiteriaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_confiteria, parent, false)
        return ConfiteriaViewHolder(view)
    }

    override fun onBindViewHolder(holder: ConfiteriaViewHolder, position: Int) {
        val item = items[position]
        holder.image.setImageResource(item.image)
        holder.name.text = item.name
        holder.price.text = item.price
        holder.description.text = item.description
        holder.quantity.text = "0"

        holder.increaseButton.setOnClickListener {
            val currentQuantity = holder.quantity.text.toString().toInt()
            holder.quantity.text = (currentQuantity + 1).toString()
            onQuantityChanged()
        }

        holder.decreaseButton.setOnClickListener {
            val currentQuantity = holder.quantity.text.toString().toInt()
            if (currentQuantity > 0) {
                holder.quantity.text = (currentQuantity - 1).toString()
                onQuantityChanged()
            }
        }
    }

    override fun getItemCount(): Int = items.size

    class ConfiteriaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.item_image)
        val name: TextView = view.findViewById(R.id.item_name)
        val price: TextView = view.findViewById(R.id.item_price)
        val description: TextView = view.findViewById(R.id.item_description)
        val quantity: TextView = view.findViewById(R.id.item_quantity)
        val increaseButton: Button = view.findViewById(R.id.btn_increase)
        val decreaseButton: Button = view.findViewById(R.id.btn_minus)
    }
}
