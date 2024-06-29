package com.example.proyectoandroid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class TicketItem(val type: String, val price: String, val description: String)

class TicketAdapter(
    private val ticketList: List<TicketItem>,
    private val onQuantityChanged: () -> Unit
) : RecyclerView.Adapter<TicketAdapter.TicketViewHolder>() {

    class TicketViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ticketImage: ImageView = itemView.findViewById(R.id.ticket_image)
        val ticketType: TextView = itemView.findViewById(R.id.ticket_type)
        val ticketPrice: TextView = itemView.findViewById(R.id.ticket_price)
        val ticketDescription: TextView = itemView.findViewById(R.id.ticket_description)
        val ticketQuantity: Spinner = itemView.findViewById(R.id.ticket_quantity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.ticket_item, parent, false)
        return TicketViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TicketViewHolder, position: Int) {
        val ticketItem = ticketList[position]
        holder.ticketImage.setImageResource(R.drawable.asientos)
        holder.ticketType.text = ticketItem.type
        holder.ticketPrice.text = "valor: ${ticketItem.price}"
        holder.ticketDescription.text = ticketItem.description

        val adapter = ArrayAdapter.createFromResource(
            holder.itemView.context,
            R.array.ticket_quantities,
            R.layout.spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        holder.ticketQuantity.adapter = adapter

        holder.ticketQuantity.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                onQuantityChanged()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
    }

    override fun getItemCount() = ticketList.size
}
