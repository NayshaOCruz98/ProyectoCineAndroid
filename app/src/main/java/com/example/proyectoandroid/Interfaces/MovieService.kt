package com.example.proyectoandroid.Interfaces

import com.example.proyectoandroid.Entities.MovieResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header

interface MovieService {
    @GET("movie/list")
    suspend fun getMovies(): Response<MovieResponse>
}

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://192.168.18.8:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val movieApi: MovieService by lazy {
        retrofit.create(MovieService::class.java)
    }

}