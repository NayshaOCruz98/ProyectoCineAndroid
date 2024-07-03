package com.example.proyectoandroid.UI

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
import com.example.proyectoandroid.R
import com.squareup.picasso.Picasso
import org.w3c.dom.Text

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
        val tvGenero = findViewById<TextView>(R.id.tv_genero)
        val tvDuracion = findViewById<TextView>(R.id.tv_duracion)
        val tvDirector = findViewById<TextView>(R.id.tv_director)

        // Obtener los datos del Intent
        val title = intent.getStringExtra("nombre") ?: ""
        val description = intent.getStringExtra("descripcion") ?: ""
        val trailerUrl = intent.getStringExtra("trailerurl") ?: ""
        val imageUrl = intent.getStringExtra("imgurl")
        val txtGenero = intent.getStringExtra("genero")
        val txtDirector = intent.getStringExtra("directorpelicula")
        val txtDuracion = intent.getStringExtra("duracionpelicula")

        if (imageUrl != null && imageUrl.isNotEmpty()) {
            Picasso.get().load(imageUrl).into(trailerBanner)
        }

        // Configurar los datos en las vistas
        movieTitle.text = title
        movieDescription.text = description
        tvGenero.text = txtGenero
        tvDirector.text = txtDirector
        tvDuracion.text = txtDuracion

        // Configurar el clic en el botón para ver el tráiler
        watchTrailerButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(trailerUrl))
            startActivity(intent)
        }

        buyButton.setOnClickListener {
            val intent = Intent(this, ComprasEntradaActivity::class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}