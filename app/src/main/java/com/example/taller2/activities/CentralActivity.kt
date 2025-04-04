package com.example.taller2.activities


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.taller2.R
import java.text.SimpleDateFormat
import java.util.*

class CentralActivity : AppCompatActivity() {

    private lateinit var horasLaboralesInput: EditText
    private lateinit var tareasPendientesInput: EditText
    private lateinit var nivelDificultadInput: SeekBar
    private lateinit var descansoInput: EditText
    private lateinit var calcularButton: Button
    private lateinit var resultadoTextView: TextView
    private lateinit var guardarButton: Button
    private lateinit var historialListView: ListView

    private lateinit var sharedPreferences: SharedPreferences
    private val historialList = mutableListOf<String>()
    private lateinit var historialAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_central)

        // Vinculación con UI
        horasLaboralesInput = findViewById(R.id.horasLaborales)
        tareasPendientesInput = findViewById(R.id.tareasPendientesInput)
        nivelDificultadInput = findViewById(R.id.nivelDificultadInput)
        descansoInput = findViewById(R.id.descansoInput)
        calcularButton = findViewById(R.id.calcularButton)
        resultadoTextView = findViewById(R.id.resultadoTextView)
        guardarButton = findViewById(R.id.guardarButton)
        historialListView = findViewById(R.id.historialListView)

        sharedPreferences = getSharedPreferences("HistorialProductividad", Context.MODE_PRIVATE)

        // Cargar historial
        cargarHistorial()

        calcularButton.setOnClickListener {
            calcularProductividad()
        }

        guardarButton.setOnClickListener {
            guardarResultado()
        }
    }

    private fun calcularProductividad() {
        val horas = horasLaboralesInput.text.toString().toDoubleOrNull() ?: 0.0
        val tareas = tareasPendientesInput.text.toString().toIntOrNull() ?: 1
        val dificultad = nivelDificultadInput.progress + 1
        val descanso = descansoInput.text.toString().toDoubleOrNull() ?: 0.0

        // Fórmulas de productividad
        val distribucionOptima = horas * (dificultad / 5.0)
        val productividadEstimada = (100 - (descanso * 2)) * (dificultad / 5.0)
        val tiempoTotalRequerido = (tareas * dificultad) / horas

        val resultado = """
            Distribución Óptima: ${"%.2f".format(distribucionOptima)} horas
            Productividad Estimada: ${"%.2f".format(productividadEstimada)}%
            Tiempo Total Requerido: ${"%.2f".format(tiempoTotalRequerido)} horas
        """.trimIndent()

        resultadoTextView.text = resultado
    }

    private fun guardarResultado() {
        val resultado = resultadoTextView.text.toString()
        if (resultado.isNotEmpty()) {
            val fecha = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(Date())
            val registro = "$fecha\n$resultado"

            historialList.add(0, registro)
            historialAdapter.notifyDataSetChanged()

            val editor = sharedPreferences.edit()
            editor.putString("registro_$fecha", registro)
            editor.apply()

            Toast.makeText(this, "Resultado guardado", Toast.LENGTH_SHORT).show()
        }
    }

    private fun cargarHistorial() {
        historialList.clear()
        sharedPreferences.all.values.forEach {
            historialList.add(it.toString())
        }

        historialAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, historialList)
        historialListView.adapter = historialAdapter
    }
}
