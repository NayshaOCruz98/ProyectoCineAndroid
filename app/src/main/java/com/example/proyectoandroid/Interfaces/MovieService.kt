package com.example.proyectoandroid.Interfaces

import com.example.proyectoandroid.Entities.MovieResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface MovieService {
    @GET("movie/find")
    suspend fun getMovies(): MovieResponse
}

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: MovieService by lazy {
        retrofit.create(MovieService::class.java)
    }
}