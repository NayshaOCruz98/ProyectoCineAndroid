package com.example.proyectoandroid

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MenuPrincipalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu_principal)

        // Obtener una referencia al RecyclerView
        val rvPremiere = findViewById<RecyclerView>(R.id.rv_premiere)

        // Crear una lista de películas de ejemplo
        val movies = listOf(
            Movie("Intensamente 2", "Las pequeñas voces dentro de la cabeza de Riley la conocen intensamente, pero el próximo año todo cambiará cuando INTENSA-MENTE 2 de Disney y Pixar introduzca una nueva emoción: Ansiedad. Según la directora Kelsey Mann, el nuevo personaje promete causar revuelo en el cuartel general.", R.drawable.movie1, "https://www.youtube.com/watch?v=AcHpm01MCtI&t=4s"),
            Movie("Madame Web", "Una paramédica de Manhattan que se ve obligada a enfrentarse a revelaciones sobre su pasado forja una relación con tres jóvenes destinadas a tener un futuro poderoso, si consiguen sobrevivir a un presente mortal.", R.drawable.movie2, "https://www.youtube.com/watch?v=nqOjhXRrehA"),
            Movie("Bad Boy", "Este verano, los Bad Boys favoritos del mundo están de regreso con su icónica mezcla de acción al borde de su asiento y comedia escandalosa, pero esta vez con un giro: los mejores de Miami ahora están huyendo.", R.drawable.movie3, "https://www.youtube.com/watch?v=UeC-kFsEkP8")
            // Agrega más películas aquí
        )

        // Crear una instancia del adaptador y configurarlo en el RecyclerView
        val movieAdapter = MovieAdapter(movies)
        rvPremiere.adapter = movieAdapter

        // Configurar el layout manager
        rvPremiere.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}