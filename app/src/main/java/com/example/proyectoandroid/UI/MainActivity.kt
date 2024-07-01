package com.example.proyectoandroid.UI

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectoandroid.Entities.LoginRequest
import com.example.proyectoandroid.Interfaces.RetrofitInstance2
import com.example.proyectoandroid.R
import com.example.proyectoandroid.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding // Declara la variable de enlace

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) // Infla la vista usando View Binding
        setContentView(binding.root) // Establece la vista raíz del enlace

        binding.btnIngresar.setOnClickListener {
            val email = binding.edtUsuario.text.toString()
            val password = binding.edtClave.text.toString()

            val loginRequest = LoginRequest(email, password)

            GlobalScope.launch(Dispatchers.Main) {
                try {
                    val response = withContext(Dispatchers.IO) {
                        RetrofitInstance2.userService.login(loginRequest)
                    }

                    if (response.code == 1) {
                        val token = response.token
                        // Manejar el token recibido
                        if (token != null) {
                            navigateToMenuPrincipalActivity(token)
                        }
                    } else {
                        val errorMessage = response.error ?: "Error desconocido"
                        // Manejar el error
                    }
                } catch (e: Exception) {
                    // Manejar errores de red u otros
                }
            }
        }
    }

    private fun navigateToMenuPrincipalActivity(token: String) {
        val intent = Intent(this, MenuPrincipalActivity::class.java).apply {
            putExtra("token", token)
        }
        startActivity(intent)
        finish() // Opcional, dependiendo de si deseas que LoginActivity se mantenga en la pila de actividades
    }

}