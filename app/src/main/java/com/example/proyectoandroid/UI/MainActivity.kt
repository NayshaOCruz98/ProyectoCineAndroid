package com.example.proyectoandroid.UI

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
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
        enableEdgeToEdge()

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
//                        val token = response.token
                        // Manejar el token recibido
                            navigateToMenuPrincipalActivity()
                    } else {
                        val errorMessage = response.error ?: "Error desconocido"
                        Toast.makeText(applicationContext,"Error, credenciales incorrectas",Toast.LENGTH_LONG).show()
                        // Manejar el error
                    }
                } catch (e: Exception) {
                    // Manejar errores de red u otros
                    Toast.makeText(applicationContext,"Error, credenciales incorrectas",Toast.LENGTH_LONG).show()
                }
            }
        }

        binding.btnRegistrar.setOnClickListener {
            navigateToRegisterActivity()
        }
    }

    private fun navigateToMenuPrincipalActivity() {
        val intent = Intent(this, MenuPrincipalActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToRegisterActivity() {
        val intent = Intent(this, RegistrarUserActivity::class.java)
        startActivity(intent)
    }

}