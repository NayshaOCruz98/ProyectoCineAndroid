package com.example.proyectoandroid

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class StepActivity : AppCompatActivity() {
    private val selectedSeats = mutableSetOf<String>()
    private lateinit var seatCountTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_step)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.stepmain)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        seatCountTextView = findViewById(R.id.seatCount)
        val continueButton = findViewById<Button>(R.id.continueButton)

        continueButton.setOnClickListener {
            val intent = Intent(this, ConfiteriaActivity::class.java)
            startActivity(intent)
        }

        val seatButtonIds = listOf(
            R.id.seat_1, R.id.seat_2, R.id.seat_3, R.id.seat_4, R.id.seat_5, R.id.seat_6,
            R.id.seat_7, R.id.seat_8, R.id.seat_9, R.id.seat_10, R.id.seat_11, R.id.seat_12,
            R.id.seat_13, R.id.seat_14, R.id.seat_15, R.id.seat_16, R.id.seat_17, R.id.seat_18,
            R.id.seat_19, R.id.seat_20, R.id.seat_21, R.id.seat_22, R.id.seat_23, R.id.seat_24,
            R.id.seat_25, R.id.seat_26, R.id.seat_27, R.id.seat_28, R.id.seat_29, R.id.seat_30,
            R.id.seat_31, R.id.seat_32, R.id.seat_33, R.id.seat_34, R.id.seat_35, R.id.seat_36
        )

        for (id in seatButtonIds) {
            val seatButton: Button = findViewById(id)
            seatButton.setOnClickListener {
                val seat = seatButton.text.toString()
                if (selectedSeats.contains(seat)) {
                    selectedSeats.remove(seat)
                    seatButton.setBackgroundResource(R.drawable.ic_silla_vector)
                } else {
                    selectedSeats.add(seat)
                    seatButton.setBackgroundResource(R.drawable.ic_silla_vector_selected)
                }
                updateSeatCount()
            }
        }
    }

    private fun updateSeatCount() {
        seatCountTextView.text = selectedSeats.size.toString()
    }
}
