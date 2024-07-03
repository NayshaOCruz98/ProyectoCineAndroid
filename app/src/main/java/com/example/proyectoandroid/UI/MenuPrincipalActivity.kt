package com.example.proyectoandroid.UI

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectoandroid.Adapters.MovieAdapter
import com.example.proyectoandroid.Entities.Movie
import com.example.proyectoandroid.Interfaces.RetrofitInstance
import com.example.proyectoandroid.R
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MenuPrincipalActivity : AppCompatActivity() {
    private lateinit var movieAdapter: MovieAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_principal)
        enableEdgeToEdge()

//        val token = intent.getStringExtra("token")

        val recyclerView = findViewById<RecyclerView>(R.id.rv_premiere)
        recyclerView.layoutManager = GridLayoutManager(this,2)
        movieAdapter = MovieAdapter(movies = mutableListOf(), this)
        recyclerView.adapter = movieAdapter

        lifecycleScope.launch {
            val movies = getMovies()
            movieAdapter.updateMovies(movies)
        }
    }

    private suspend fun getMovies(): List<Movie> {
        return withContext(Dispatchers.IO) {
                val response = RetrofitInstance.movieApi.getMovies()
                if (response.isSuccessful) {
                    response.body()?.body ?: emptyList()
                } else {
                    // Manejar errores de la respuesta aquí si es necesario
                    emptyList()
                }
        }
    }
}