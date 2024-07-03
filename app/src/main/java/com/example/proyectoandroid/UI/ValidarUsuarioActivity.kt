package com.example.proyectoandroid.UI

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.proyectoandroid.Entities.RegisterRequest
import com.example.proyectoandroid.Entities.VerifyRequest
import com.example.proyectoandroid.Entities.VerifyResponse
import com.example.proyectoandroid.Interfaces.RetrofitInstance2
import com.example.proyectoandroid.R
import com.example.proyectoandroid.databinding.ActivityRegistrarUserBinding
import com.example.proyectoandroid.databinding.ActivityValidarUsuarioBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ValidarUsuarioActivity : AppCompatActivity() {

    private lateinit var binding: ActivityValidarUsuarioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityValidarUsuarioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val intent = intent
        val correo = intent.getStringExtra("email_val")

        binding.edtCorreoVal.setText(correo)
        binding.edtCorreoVal.isEnabled = false

        binding.btnRegresarVal.setOnClickListener {
            navigateToRegistrarActivity()
        }

        binding.btnRegistrarVal.setOnClickListener {
            val email = binding.edtCorreoVal.text.toString()
            val code = binding.edtCodigoVal.text.toString()
            val verifyRequest = VerifyRequest(code, email)

            GlobalScope.launch(Dispatchers.Main) {
                try {
                    val response = withContext(Dispatchers.IO) {
                        RetrofitInstance2.verifyService.verify(verifyRequest)
                    }

                    if (response.http == "OK") {
                        navigateToMenuPrincipalActivity()
                    } else {
                        Toast.makeText(applicationContext,"Error, codigo incorrecto", Toast.LENGTH_LONG).show()
                        // Manejar el error
                    }
                } catch (e: Exception) {
                    // Manejar errores de red u otros
                    Toast.makeText(applicationContext,"El token ha expirado", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun navigateToMenuPrincipalActivity() {
        val intent = Intent(this, MenuPrincipalActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToRegistrarActivity() {
        val intent = Intent(this, RegistrarUserActivity::class.java)
        startActivity(intent)
    }
}