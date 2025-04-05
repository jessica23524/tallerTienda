package com.example.taller2.fragments

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taller2.R

class PerfilFragment : Fragment() {

    private lateinit var nombreInput: TextView
    private lateinit var apellidoInput: TextView
    private lateinit var correoInput: TextView
    private lateinit var telefonoInput: TextView
    private lateinit var imgPerfil: ImageView
    private lateinit var btnEditar: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val vista = inflater.inflate(R.layout.fragment_perfil, container, false)

        nombreInput = vista.findViewById(R.id.NombreUsuarioo)
        apellidoInput = vista.findViewById(R.id.ApellidoUsua)
        correoInput = vista.findViewById(R.id.CorreoUsua)
        telefonoInput = vista.findViewById(R.id.telefono)
        imgPerfil = vista.findViewById(R.id.ImagenUsuario)
        btnEditar = vista.findViewById(R.id.editardatos)

        cargarDatos()

        btnEditar.setOnClickListener {
            findNavController().navigate(R.id.action_editarperfilde_perfilFragment)
        }

        return vista
    }

    override fun onResume() {
        super.onResume()
        cargarDatos() // <-- Aquí se recargan los datos cada vez que se vuelve a este fragmento
    }

    private fun cargarDatos() {
        val sharedPreferences: SharedPreferences = requireContext()
            .getSharedPreferences("userData", Context.MODE_PRIVATE)

        val nombres = sharedPreferences.getString("nombres", "No registrado")
        val apellidos = sharedPreferences.getString("apellidos", "No registrado")
        val correo = sharedPreferences.getString("correo", "No registrado")
        val telefono = sharedPreferences.getString("telefono", "No registrado")

        nombreInput.text = nombres
        apellidoInput.text = apellidos
        correoInput.text = correo
        telefonoInput.text = telefono
    }
}