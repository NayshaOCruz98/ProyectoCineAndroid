package com.example.proyectoandroid

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ConfiteriaActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ConfiteriaAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_confiteria)
        val confiteriaItems = listOf(
            ConfiteriaItem(R.drawable.combo_salado, "COMBO DUO VASO ", "S/ 75.90", "1 VASO GIGANTE CON GASEOSA + 1 VASO GRANDE CON GASEOSA + 2 CANCHITAS GRANDES SALADA +1 AGUA"),
            ConfiteriaItem(R.drawable.combo_1, "COMBO TRIO SAL", "S/ 87.90", "3 CANCHITAS MEDIANAS SALADAS + 3 GASEOSAS MEDIANAS"),
            ConfiteriaItem(R.drawable.combo_2, "COMBO DUO ", "S/ 67.60", "2 CANCHITAS GRANDES SALADAS + 2 VASO GRANDE CON GASEOSA"),
            ConfiteriaItem(R.drawable.combo_4, "COMBO 1 SAL", "S/ 31.80", "1 CANCHITA MEDIANA C/SAL + 1 GASEOSAS MEDIANA"),
        )
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ConfiteriaAdapter(confiteriaItems)
        recyclerView.adapter = adapter
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}