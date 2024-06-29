package com.example.proyectoandroid

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ComprasEntradaActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TicketAdapter
    private val ticketList = listOf(
        TicketItem("PROMO ONLINE HASTA 35% 2D", "S/ 13.44", ""),
        TicketItem("ADULTO 2D", "S/ 19.60", ""),
        TicketItem("ADULTO MAYOR 2D", "S/ 17.60", ""),
        TicketItem("P. CON DISCAPACIDAD 2D", "S/ 14.40", "Ley N°29973..."),
        TicketItem("SILLA DE RUEDAS 2D", "S/ 14.40", "")
    )
    private lateinit var totalTickets: TextView
    private lateinit var totalPrice: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_compras_entrada)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = TicketAdapter(ticketList) { updateTotals() }
        recyclerView.adapter = adapter

        totalTickets = findViewById(R.id.total_tickets)
        totalPrice = findViewById(R.id.total_price)

        val continueButton = findViewById<Button>(R.id.continue_button)
        continueButton.setOnClickListener {
            val intent = Intent(this, StepActivity::class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Comprasmain)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun updateTotals() {
        var totalTicketCount = 0
        var totalTicketPrice = 0.0

        for (i in 0 until adapter.itemCount) {
            val viewHolder = recyclerView.findViewHolderForAdapterPosition(i) as TicketAdapter.TicketViewHolder
            val quantity = viewHolder.ticketQuantity.selectedItem.toString().toInt()
            val price = ticketList[i].price.removePrefix("S/ ").toDouble()

            totalTicketCount += quantity
            totalTicketPrice += quantity * price
        }

        totalTickets.text = "Total de Boletos: $totalTicketCount"
        totalPrice.text = "Total Precio: S/ %.2f".format(totalTicketPrice)
    }
}
