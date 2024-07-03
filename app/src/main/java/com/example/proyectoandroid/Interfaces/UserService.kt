package com.example.proyectoandroid.Interfaces

import com.example.proyectoandroid.Entities.LoginRequest
import com.example.proyectoandroid.Entities.LoginResponse
import com.example.proyectoandroid.Entities.RegisterRequest
import com.example.proyectoandroid.Entities.RegisterResponse
import com.example.proyectoandroid.Entities.VerifyRequest
import com.example.proyectoandroid.Entities.VerifyResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("user/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse
}
interface UserRegisterService {
    @POST("user/registrate")
    suspend fun registrate(@Body request: RegisterRequest): RegisterResponse
}

interface VerifyService{
    @POST("user/verifyRegistration")
    suspend fun verify(@Body request: VerifyRequest): VerifyResponse
}

object RetrofitInstance2 {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://192.168.18.8:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val userService: UserService by lazy {
        retrofit.create(UserService::class.java)
    }

    val registerUserService: UserRegisterService by lazy {
        retrofit.create(UserRegisterService::class.java)
    }

    val verifyService: VerifyService by lazy {
        retrofit.create(VerifyService::class.java)
    }
}