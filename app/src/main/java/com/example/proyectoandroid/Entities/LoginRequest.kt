package com.example.proyectoandroid.Entities

data class LoginRequest(
    val email: String,
    val password: String
)

data class LoginResponse(
    val code: Int,
    val error: String?,
    val token: String?
)