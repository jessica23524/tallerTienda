package com.example.taller2.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.taller2.R

class RegistroActivity : AppCompatActivity() {

    private lateinit var editTextNombres: EditText
    private lateinit var editTextApellidos: EditText
    private lateinit var editTextCorreo: EditText
    private lateinit var editTextTelefono: EditText
    private lateinit var editTextConfirmarPassword:EditText
    private lateinit var editTextPassword: EditText
    private lateinit var checkBoxTerminos: CheckBox
    private lateinit var buttonRegistro: Button
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        Log.d("RegistroActivity", "onCreate: Inicializando el activity de registro")

        //  Inicializar vistas
        editTextNombres = findViewById(R.id.NombreUsuario)
        editTextApellidos = findViewById(R.id.Apellido)
        editTextCorreo = findViewById(R.id.correo)
        editTextTelefono = findViewById(R.id.telefono)
        editTextConfirmarPassword = findViewById(R.id.textPassword)
        editTextPassword = findViewById(R.id.PasswordU)
        checkBoxTerminos = findViewById(R.id.Terminos)
        buttonRegistro = findViewById(R.id.buttonRegistro)

        // Archivo de almacenamiento local
        sharedPreferences = getSharedPreferences("userData", MODE_PRIVATE)

        buttonRegistro.setOnClickListener {
            if (validarCampos()) {
                guardarDatos()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun validarCampos(): Boolean {
        val nombres = editTextNombres.text.toString().trim()
        val apellidos = editTextApellidos.text.toString().trim()
        val correo = editTextCorreo.text.toString().trim()
        val telefono = editTextTelefono.text.toString().trim()
        val confirmarcontrasena = editTextConfirmarPassword.text.toString().trim()
        val password = editTextPassword.text.toString().trim()

        if (nombres.isEmpty() || apellidos.isEmpty() || correo.isEmpty() || telefono.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
            return false
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            Toast.makeText(this, "Correo electrónico inválido", Toast.LENGTH_SHORT).show()
            return false
        }
        if (password != confirmarcontrasena) {
            Toast.makeText(this, "La contraseña no coincide", Toast.LENGTH_SHORT).show()
            return false
        }
        if (!checkBoxTerminos.isChecked) {
            Toast.makeText(this, "Debes aceptar los términos y condiciones", Toast.LENGTH_SHORT)
                .show()
            return false
        }
        return true
    }

    private fun guardarDatos() {
        val nombres = editTextNombres.text.toString().trim()
        val apellidos = editTextApellidos.text.toString().trim()
        val correo = editTextCorreo.text.toString().trim()
        val telefono = editTextTelefono.text.toString().trim()
        val passwordU = editTextPassword.text.toString().trim()
        val aceptaTerminos = checkBoxTerminos.isChecked

        Log.d(
            "RegistroActivity",
            "Guardando datos: nombres=$nombres, apellidos=$apellidos, correo=$correo, telefono=$telefono, password=$passwordU"
        )

        val editor = sharedPreferences.edit()
        editor.putString("nombres", nombres)
        editor.putString("apellidos", apellidos)
        editor.putString("correo", correo)
        editor.putString("telefono", telefono)
        editor.putString("password", passwordU)
        editor.putBoolean("terminosAceptados", aceptaTerminos)  // Guardar si aceptó términos

        editor.apply()  // Guardar cambios

        Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
    }
}
