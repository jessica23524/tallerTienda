package com.example.taller2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taller2.R

class CategoriaFragments : Fragment() {

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val vista = inflater.inflate(R.layout.fragment_categorias, container, false)

            // findViewById SOLO FUNCIONA DESPUÉS DE inflar la vista
        val frameElectronica = vista.findViewById<FrameLayout>(R.id.frameElectronica)
        val frameRopa = vista.findViewById<FrameLayout>(R.id.frameRopa)
        val frameHogar = vista.findViewById<FrameLayout>(R.id.frameHogar)
        val frameDeportes = vista.findViewById<FrameLayout>(R.id.frameDeportes)
        val frameAccesorios = vista.findViewById<FrameLayout>(R.id.frameAccesorios)

        // Agregar onClickListeners para cada categoría
        frameElectronica.setOnClickListener {
            navegarConCategoria("Electrónica")
        }
        frameRopa.setOnClickListener {
            navegarConCategoria("Ropa")
        }
        frameHogar.setOnClickListener {
            navegarConCategoria("Hogar")
        }
        frameDeportes.setOnClickListener {
            navegarConCategoria("Deportes")
        }
        frameAccesorios.setOnClickListener {
            navegarConCategoria("Accesorios")
        }

        return vista
    }

    private fun navegarConCategoria(nombreCategoria: String) {
        val bundle = Bundle()
        bundle.putString("categoria", nombreCategoria)
        findNavController().navigate(R.id.action_categoriaFragments_to_listaPorCategoriaFragment, bundle)
    }
}
