package com.example.proyectoandroid.Entities

data class Movie(
    val idpelicula: Int,
    val nombrepelicula: String,
    val descripcion: String,
    val genero: String,
    val trailerurl: String,
    val imgurl: String,
    val directorpelicula: String,
    val duracionpelicula: String
)

data class MovieResponse(
    val http: String,
    val body: List<Movie>,
    val error: String
)