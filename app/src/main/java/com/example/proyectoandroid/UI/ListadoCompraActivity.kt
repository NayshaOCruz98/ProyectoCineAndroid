package com.example.proyectoandroid.UI

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.proyectoandroid.R

class ListadoCompraActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_listado_compra)
        // Recuperar información de los intents
        val totalTickets = intent.getIntExtra("TOTAL_TICKETS", 0)
        val totalPrecioEntradas = intent.getDoubleExtra("TOTAL_PRECIO_ENTRADAS", 0.0)
        val confiteriaSeleccionada = intent.getStringExtra("CONFITERIA_SELECCIONADA") ?: ""
        val precioConfiteria = intent.getDoubleExtra("PRECIO_CONFITERIA", 0.0)

        // Encontrar las vistas
        val tvAsientos = findViewById<TextView>(R.id.tvAsientos)
        val tvEntradas = findViewById<TextView>(R.id.tvEntradas)
        val tvAlimentos = findViewById<TextView>(R.id.tvAlimentos)
        val tvTotal = findViewById<TextView>(R.id.tvTotal)

        // Configurar las vistas
        tvAsientos.text = "ASIENTO GENERAL: J7, J6"
        tvEntradas.text = "$totalTickets PROMO ONLINE HASTA 35% 2D S/ %.2f\n* INCLUYE EL CARGO POR SERVICIO ONLINE".format(totalPrecioEntradas)
        tvAlimentos.text = "ALIMENTOS Y BEBIDAS\n1 $confiteriaSeleccionada S/ %.2f\nCARGO SERVICIO CONFITERÍA S/ 0.70".format(precioConfiteria)

        val total = totalPrecioEntradas + precioConfiteria + 0.70
        tvTotal.text = "TOTAL\nS/ %.2f".format(total)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}