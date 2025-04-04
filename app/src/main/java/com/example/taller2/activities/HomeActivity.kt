package com.example.taller2.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.taller2.R

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        // Vincular botones con su ID
        val buttonComienza = findViewById<Button>(R.id.buttonComienza1)
        val buttonRegistro = findViewById<TextView>(R.id.registrarHome)

        buttonComienza.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        // Cuando se presiona "Regístrate", ir a Registro
        buttonRegistro.setOnClickListener {
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }
    }
}