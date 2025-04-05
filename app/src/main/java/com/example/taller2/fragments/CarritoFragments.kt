package com.example.taller2.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taller2.CarritoAdapter
import com.example.taller2.ProductoCarrito
import com.example.taller2.R

class CarritoFragments : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var txtCantidad: TextView
    private lateinit var txtTotal: TextView
    private lateinit var btnFinalizarCompra: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val vista = inflater.inflate(R.layout.fragment_carrito, container, false)

        recyclerView = vista.findViewById(R.id.rvProductosCarrito)
        recyclerView.layoutManager = LinearLayoutManager(requireContext()) // ← IMPORTANTE
        txtCantidad = vista.findViewById(R.id.txtTotalProductos)
        txtTotal = vista.findViewById(R.id.txtPrecioTotal)
        btnFinalizarCompra = vista.findViewById(R.id.btnPagar)

        cargarDatosCarrito()

        btnFinalizarCompra.setOnClickListener {
            Toast.makeText(requireContext(), "Compra finalizada con éxito", Toast.LENGTH_SHORT).show()
            limpiarCarrito()
        }

        return vista
    }

    private fun cargarDatosCarrito() {
        val prefs = requireContext().getSharedPreferences("carrito", Context.MODE_PRIVATE)
        val nombre = prefs.getString("producto", "Ninguno") ?: "Ninguno"
        val cantidad = prefs.getInt("cantidad", 0)
        val precio = prefs.getFloat("precio", 0f)
        val total = cantidad * precio

        val productos = listOf(ProductoCarrito(nombre, cantidad, precio))
        recyclerView.adapter = CarritoAdapter(productos)

        txtCantidad.text = cantidad.toString()
        txtTotal.text = total.toString()
    }

    private fun limpiarCarrito() {
        val prefs = requireContext().getSharedPreferences("carrito", Context.MODE_PRIVATE)
        prefs.edit().clear().apply()
        cargarDatosCarrito()
    }
}
