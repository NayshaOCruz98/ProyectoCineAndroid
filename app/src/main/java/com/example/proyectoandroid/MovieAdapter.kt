package com.example.proyectoandroid

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class MovieAdapter(private val movies: List<Movie>) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = movies.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val posterImageView: ImageView = itemView.findViewById(R.id.movie_poster1)

        fun bind(movie: Movie) {
            posterImageView.setImageResource(movie.poster)

            itemView.setOnClickListener {
                val context = itemView.context
                val intent = Intent(context, MovieDetailsActivity::class.java).apply {
                    putExtra("title", movie.title)
                    putExtra("description", movie.description)
                    putExtra("poster", movie.poster)
                    putExtra("trailerUrl", movie.trailerUrl)
                }
                context.startActivity(intent)
            }
        }
    }
}
