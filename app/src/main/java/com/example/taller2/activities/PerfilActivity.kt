package com.example.taller2.activities


import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.taller2.R

class PerfilActivity : AppCompatActivity() {

    private lateinit var nombreInput: EditText
    private lateinit var apellidoInput: EditText
    private lateinit var correoInput: EditText
    private lateinit var telefonoInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_perfil)


        // Vincular elementos d
        nombreInput = findViewById(R.id.NombreUsuarioo)
        apellidoInput = findViewById(R.id.ApellidoUsua)
        correoInput = findViewById(R.id.CorreoUsua)
        telefonoInput = findViewById(R.id.telefono)

        //  Cargar los datos guardados en SharedPreferences
        cargarDatos()
    }

    private fun cargarDatos() {
        val sharedPreferences: SharedPreferences = getSharedPreferences("userData", MODE_PRIVATE)

        // Recuperar los datos guardados, si no existen se mostrará "No registrado"
        val nombres = sharedPreferences.getString("nombres", "No registrado")
        val apellidos = sharedPreferences.getString("apellidos", "No registrado")
        val correo = sharedPreferences.getString("correo", "No registrado")
        val telefono = sharedPreferences.getString("telefono", "No registrado")

        // Mostrar los datos en los EditText
        nombreInput.setText(nombres)
        apellidoInput.setText(apellidos)
        correoInput.setText(correo)
        telefonoInput.setText(telefono)

        val buttonSalud = findViewById<Button>(R.id.salud)
        buttonSalud.setOnClickListener {
            startActivity(Intent(this, CentralActivity::class.java))
        }
    }
}






