package com.example.proyectoandroid.UI

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import android.window.BackEvent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.proyectoandroid.Entities.LoginRequest
import com.example.proyectoandroid.Entities.RegisterRequest
import com.example.proyectoandroid.Interfaces.RetrofitInstance2
import com.example.proyectoandroid.R
import com.example.proyectoandroid.databinding.ActivityMainBinding
import com.example.proyectoandroid.databinding.ActivityRegistrarUserBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegistrarUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrarUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrarUserBinding.inflate(layoutInflater) // Infla la vista usando View Binding
        setContentView(binding.root) // Establece la vista raíz del enlace
        enableEdgeToEdge()

        binding.btnRegistrar.setOnClickListener {
            val email = binding.edtCorreo.text.toString()
            val password = binding.edtClave.text.toString()
            val apellidos = binding.edtApellidos.text.toString()
            val nombres = binding.edtNombres.text.toString()

            val registerRequest = RegisterRequest(email,password,nombres,apellidos)

            GlobalScope.launch(Dispatchers.Main) {
                try {
                    val response = withContext(Dispatchers.IO) {
                        RetrofitInstance2.registerUserService.registrate(registerRequest)
                    }

                    if (response.http == "OK") {
                        navigateToVerifyActivity()
                    } else {
                        Toast.makeText(applicationContext,"Error, credenciales incorrectas", Toast.LENGTH_LONG).show()
                        // Manejar el error
                    }
                } catch (e: Exception) {
                    // Manejar errores de red u otros
                    Toast.makeText(applicationContext,e.stackTraceToString(), Toast.LENGTH_LONG).show()
                }
            }
        }

        binding.btnVolver.setOnClickListener {
            navigateToLoginActivity()
        }
    }

    private fun navigateToLoginActivity() {
        val email = binding.edtCorreo.text.toString()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToVerifyActivity() {
        val email = binding.edtCorreo.text.toString()
        val intent = Intent(this, ValidarUsuarioActivity::class.java)
        intent.putExtra("email_val",email)
        startActivity(intent)
    }
}