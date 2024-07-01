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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_principal)

        val token = intent.getStringExtra("token")

        val recyclerView: RecyclerView = findViewById(R.id.rv_premiere)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        lifecycleScope.launch {
            val movies = getMovies(token)
            recyclerView.adapter = MovieAdapter(movies, this@MenuPrincipalActivity)
        }
    }

    private suspend fun getMovies(token:String?): List<Movie> {
        return withContext(Dispatchers.IO) {
            token?.let {
                val response = RetrofitInstance.movieApi.getMovies("Bearer $token")
                if (response.isSuccessful) {
                    response.body()?.list ?: emptyList()
                } else {
                    // Manejar errores de la respuesta aquí si es necesario
                    emptyList()
                }
            } ?: emptyList()
        }
    }
}
