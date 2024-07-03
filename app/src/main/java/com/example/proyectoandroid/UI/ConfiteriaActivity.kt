package com.example.proyectoandroid.UI

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
import com.example.proyectoandroid.Adapters.ConfiteriaAdapter
import com.example.proyectoandroid.Adapters.ConfiteriaItem
import com.example.proyectoandroid.R

class ConfiteriaActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ConfiteriaAdapter
    private lateinit var totalItems: TextView
    private lateinit var totalCost: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_confiteria)

        val confiteriaItems = listOf(
            ConfiteriaItem(R.drawable.combo_salado, "COMBO DUO VASO ", "S/ 75.90", "1 VASO GIGANTE CON GASEOSA + 1 VASO GRANDE CON GASEOSA + 2 CANCHITAS GRANDES SALADA +1 AGUA"),
            ConfiteriaItem(R.drawable.combo2, "COMBO TRIO SAL", "S/ 87.90", "3 CANCHITAS MEDIANAS SALADAS + 3 GASEOSAS MEDIANAS"),
            ConfiteriaItem(R.drawable.combo3, "COMBO DUO ", "S/ 67.60", "2 CANCHITAS GRANDES SALADAS + 2 VASO GRANDE CON GASEOSA"),
            ConfiteriaItem(R.drawable.combo4, "COMBO 1 SAL", "S/ 31.80", "1 CANCHITA MEDIANA C/SAL + 1 GASEOSAS MEDIANA")
        )

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ConfiteriaAdapter(confiteriaItems) { updateTotals() }
        recyclerView.adapter = adapter

        totalItems = findViewById(R.id.total_items)
        totalCost = findViewById(R.id.total_cost)

        findViewById<Button>(R.id.continue_button).setOnClickListener {
            val intent = Intent(this, ListadoCompraActivity::class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Confiteriamain)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun updateTotals() {
        var totalItemCount = 0
        var totalItemCost = 0.0

        for (i in 0 until adapter.itemCount) {
            val viewHolder = recyclerView.findViewHolderForAdapterPosition(i) as ConfiteriaAdapter.ConfiteriaViewHolder
            val quantity = viewHolder.quantity.text.toString().toInt()
            val price = adapter.items[i].price.removePrefix("S/ ").toDouble()

            totalItemCount += quantity
            totalItemCost += quantity * price
        }

        totalItems.text = totalItemCount.toString()
        totalCost.text = "S/ %.2f".format(totalItemCost)
    }
}
