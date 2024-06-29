package com.example.proyectoandroid

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MovieDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_movie_details)

        val trailerBanner = findViewById<ImageView>(R.id.trailer_banner)
        val watchTrailerButton = findViewById<Button>(R.id.btn_play_movie)
        val movieTitle = findViewById<TextView>(R.id.movie_title)
        val movieDescription = findViewById<TextView>(R.id.movie_description)
        val buyButton = findViewById<Button>(R.id.btn_comprar)
        // Obtener los datos del Intent
        val title = intent.getStringExtra("title") ?: ""
        val description = intent.getStringExtra("description") ?: ""
        val imageResId = intent.getIntExtra("poster", -1)
        val trailerUrl = intent.getStringExtra("trailerUrl") ?: ""

        // Configurar los datos en las vistas
        movieTitle.text = title
        movieDescription.text = description
        if (imageResId != -1) {
            trailerBanner.setImageResource(imageResId)
        }

        // Configurar el clic en el botón para ver el tráiler
        watchTrailerButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(trailerUrl))
            startActivity(intent)
        }

        buyButton.setOnClickListener {
            val intent = Intent(this, ComprasEntradaActivity::class.java)
            startActivity(intent)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Detailsmain)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
