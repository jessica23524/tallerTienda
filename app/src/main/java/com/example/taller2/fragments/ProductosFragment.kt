package com.example.taller2.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.taller2.R

class ProductosFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val vista = inflater.inflate(R.layout.fragment_productoselectrica, container, false)

        val sharedPreferences = requireContext().getSharedPreferences("carrito", Context.MODE_PRIVATE)

        val productos = listOf(
            Triple("Laptop HP", 800.0, R.id.btnAgregarCarrito),
            Triple("Auriculares", 50.0, R.id.btnAgregarCarrito2),
            Triple("Smartphone", 600.0, R.id.btnAgregarCarrito3),
        )

        productos.forEach { (nombre, precio, btnId) ->
            val btn = vista.findViewById<Button>(btnId)
            btn.setOnClickListener {
                val editor = sharedPreferences.edit()
                val cantidad = sharedPreferences.getInt(nombre, 0) + 1
                editor.putInt(nombre, cantidad)
                editor.putFloat("${nombre}_precio", precio.toFloat())
                editor.apply()
                Toast.makeText(requireContext(), "$nombre añadido al carrito", Toast.LENGTH_SHORT).show()
            }
        }

        return vista
    }
}
