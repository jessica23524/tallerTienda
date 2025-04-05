package com.example.taller2.fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taller2.R

class EditarperfilFragment : Fragment() {
    private lateinit var imagenPerfil: ImageView
    private lateinit var nombreEditText: EditText
    private lateinit var apellidoEditText: EditText
    private lateinit var correoEditText: EditText
    private lateinit var telefonoEditText: EditText
    private lateinit var guardarButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val vista = inflater.inflate(R.layout.fragment_editarperfil, container, false)

        imagenPerfil = vista.findViewById(R.id.imagenPerfil)
        nombreEditText = vista.findViewById(R.id.edtNombre)
     apellidoEditText = vista.findViewById(R.id.apellido)
        correoEditText = vista.findViewById(R.id.edtCorreo)
        telefonoEditText = vista.findViewById(R.id.edtTelefono)
        guardarButton = vista.findViewById(R.id.btnGuardarCambios)

        cargarDatos()

        imagenPerfil.setOnClickListener {
            abrirGaleria()
        }

        guardarButton.setOnClickListener {
            guardarDatos()
            Toast.makeText(requireContext(), "Cambios guardados", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.PerfilFragment) //Asegúrate de que esté en tu nav_graph.xml
        }

        return vista
    }

    private fun cargarDatos() {
        val prefs = requireContext().getSharedPreferences("userData", Context.MODE_PRIVATE)
        nombreEditText.setText(prefs.getString("nombres", ""))
        apellidoEditText.setText(prefs.getString("apellidos", ""))
        correoEditText.setText(prefs.getString("correo", ""))
        telefonoEditText.setText(prefs.getString("telefono", ""))

        // Cargar imagen desde SharedPreferences
        val uriString = prefs.getString("imagen_uri", null)
        if (!uriString.isNullOrEmpty()) {
            imagenPerfil.setImageURI(Uri.parse(uriString))
        } else {
            imagenPerfil.setImageResource(R.drawable.ic_default_profile) // Imagen por defecto
        }
    }

    private fun guardarDatos() {
        val prefs = requireContext().getSharedPreferences("userData", Context.MODE_PRIVATE)
        prefs.edit().apply {
            putString("nombres", nombreEditText.text.toString())
            putString("apellidos", apellidoEditText.text.toString())
            putString("correo", correoEditText.text.toString())
            putString("telefono", telefonoEditText.text.toString())

            // Guardar la imagen si hay una URI seleccionada
            imagenPerfil.tag?.let { uri ->
                putString("imagen_uri", uri.toString())
            }

            apply()
        }
    }

    private val seleccionarImagen = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val uri: Uri? = result.data?.data
            if (uri != null) {
                imagenPerfil.setImageURI(uri)
                imagenPerfil.tag = uri.toString() // Guardar la URI en la etiqueta del ImageView
            }
        }
    }

    private fun abrirGaleria() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        seleccionarImagen.launch(intent)
    }
}
