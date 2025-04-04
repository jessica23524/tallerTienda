package com.example.taller2.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.taller2.R

class RecuperarContrasena : AppCompatActivity() {

    private lateinit var emailInput: EditText
    private lateinit var enviarButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperacion_de_contrasema)

        // Vincular elementos con su ID
        emailInput = findViewById(R.id.Email)
        enviarButton = findViewById(R.id.buttoenviar)

        // Acción al presionar el botón "Enviar"
        enviarButton.setOnClickListener { verificarCorreo() }
    }

    private fun verificarCorreo() {
        val sharedPreferences: SharedPreferences = getSharedPreferences("userData", MODE_PRIVATE)
        val correoRegistrado =
            sharedPreferences.getString("correo", null) // Obtener correo guardado
        val correoIngresado = emailInput.text.toString().trim()

        when {
            correoIngresado.isEmpty() -> {
                Toast.makeText(this, "Por favor, ingresa un correo", Toast.LENGTH_LONG).show()
            }

            correoIngresado == correoRegistrado -> {
                Toast.makeText(this, "Correo enviado exitosamente", Toast.LENGTH_LONG).show()

                // Redirigir a LoginActivity
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish() // Cierra esta actividad
            }

            else -> {
                Toast.makeText(this, "Error: Correo no registrado", Toast.LENGTH_LONG).show()
            }
        }
    }
}