package com.example.proyectoandroid.UI

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.proyectoandroid.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


    }
    fun IniciarSesion(v : View)
    {
        val edtUsuario = findViewById<EditText>(R.id.edtUsuario)
        val edtClave = findViewById<EditText>(R.id.edtClave)

        val xUser: String = edtUsuario.text.toString()
        val xPass: String = edtClave.text.toString()

        if (xUser == "Naysha" && xPass == "Naysha123") {
            Toast.makeText(this, "Bienvenido a Cine para todos", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Error, Usuario o Clave Incorrecta", Toast.LENGTH_LONG).show()
        }

    }
}