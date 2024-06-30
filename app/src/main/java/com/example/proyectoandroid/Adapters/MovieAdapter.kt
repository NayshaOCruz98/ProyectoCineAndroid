package com.example.proyectoandroid.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectoandroid.Entities.Movie
import com.example.proyectoandroid.R
import com.example.proyectoandroid.UI.MovieDetailsActivity
import com.squareup.picasso.Picasso

class MovieAdapter(private val movies: List<Movie>, private val context: Context) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie_card, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        Picasso.get().load(movie.imgurl).into(holder.moviePoster)

        // Configurar clic en la imagen
        holder.itemView.setOnClickListener {
            // Navegar a MovieDetailsActivity
            val intent = Intent(context, MovieDetailsActivity::class.java).apply {
                // Puedes pasar datos adicionales de la película si es necesario
                putExtra("nombre", movie.nombrepelicula)
                putExtra("descripcion", movie.descripcion)
                putExtra("genero", movie.genero)
                putExtra("trailerurl", movie.trailerurl)
                putExtra("imgurl", movie.imgurl)
                putExtra("directorpelicula", movie.directorpelicula)
                putExtra("duracionpelicula", movie.duracionpelicula)
                // Agrega más extras según tus necesidades
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = movies.size

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val moviePoster: ImageView = itemView.findViewById(R.id.movie_poster1)
    }
}

