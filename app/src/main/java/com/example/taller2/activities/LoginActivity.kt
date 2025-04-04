package com.example.taller2.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.taller2.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //  Obtener referencias de los campos y botones
        val editTextUsername = findViewById<EditText>(R.id.editTextTextUsername)
        val editTextPassword = findViewById<EditText>(R.id.Password)
        val buttonIngresar = findViewById<Button>(R.id.buttoningresar)
        val textViewRecuperar = findViewById<TextView>(R.id.recuperarcontrasena)
        val textViewRegistrate = findViewById<TextView>(R.id.registrate)
        val buttonGoogle = findViewById<Button>(R.id.bt_inrGoogle)

        // Recuperar datos guardados en SharedPreferences
        val sharedPreferences: SharedPreferences = getSharedPreferences("userData", MODE_PRIVATE)
        val savedUsername = sharedPreferences.getString("nombres", "") ?: ""
        val savedPassword = sharedPreferences.getString("password", "") ?: ""

        Log.d("LoginActivity", "Datos guardados -> Nombre: $savedUsername, Contraseña: $savedPassword")

        buttonIngresar.setOnClickListener {
            val username = editTextUsername.text.toString().trim()
            val password = editTextPassword.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Log.d("LoginActivity", "Intento de inicio -> Nombre: $username, Contraseña: $password")

            if (username == savedUsername && password == savedPassword) {
                Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, PerfilActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }

        textViewRegistrate.setOnClickListener {
            startActivity(Intent(this, RegistroActivity::class.java))
        }

        textViewRecuperar.setOnClickListener {
            startActivity(Intent(this, RecuperarContrasena::class.java))
        }


    }
}
