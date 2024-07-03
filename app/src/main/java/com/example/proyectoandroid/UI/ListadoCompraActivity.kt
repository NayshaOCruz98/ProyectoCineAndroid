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
        val totalTickets = intent.getIntExtra("cantidadboletos", 0)
        val totalPrecioEntradas = intent.getDoubleExtra("preciototal_entrada", 0.0)
        val precioConfiteria = intent.getDoubleExtra("total_item_cost", 0.0)
        var nombre_pel = intent.getStringExtra("nombre_pel")

        // Encontrar las vistas
        val tvAsientos = findViewById<TextView>(R.id.tvAsientos)
        val tvEntradas = findViewById<TextView>(R.id.tvEntradas)
        val tvAlimentos = findViewById<TextView>(R.id.tvAlimentos)
        val tvTotal = findViewById<TextView>(R.id.tvTotal)
        val tvnombrePel = findViewById<TextView>(R.id.tvNombrePelicula)

        // Configurar las vistas
        tvAsientos.text = "Entradas Compradas: " + totalTickets
        tvEntradas.text = "Precio Total de las entradas: S/. " + totalPrecioEntradas
        tvAlimentos.text = "Precio Total de la Confiteria: S/. " + precioConfiteria + "\n" + "Cargo de confiteria: S/.2.20"
        tvnombrePel.text = "Pelicual Escogida: " + nombre_pel

        val total = totalPrecioEntradas + precioConfiteria + 2.20
        tvTotal.text = "Monto total a Pagar: S/." + total
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}