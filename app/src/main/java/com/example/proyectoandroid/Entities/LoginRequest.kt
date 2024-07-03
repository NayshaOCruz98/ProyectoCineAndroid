package com.example.proyectoandroid.Entities

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date

data class LoginRequest(
    val email: String,
    val password: String
)

data class LoginResponse(
    val code: Int,
    val error: String?,
    val token: String?
)

data class RegisterRequest(
    val email: String,
    val password: String,
    val nombre: String,
    val apellido: String,
)

data class RegisterResponse(
    val http: String,
    val body: String,
    val error: String
)

data class VerifyRequest(
    val code:String,
    val email:String
)

data class VerifyResponse(
    val http:String,
    val body:String,
    val error:String
)