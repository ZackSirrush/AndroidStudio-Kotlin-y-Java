package com.aint.ecuacioncircunferencia_es1921013978_annt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    private lateinit var coordenada1: EditText
    private lateinit var coordenada2: EditText
    private lateinit var longitud: EditText
    private lateinit var resolverbttn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        coordenada1 = findViewById(R.id.coordenada1)
        coordenada2 = findViewById(R.id.coordenada2)
        longitud = findViewById(R.id.longitud)
        resolverbttn = findViewById(R.id.resolverboton)

        resolverbttn.setOnClickListener{
            validarDatos()
        }

    }

    private fun validarDatos(){
        //Retoma valores de los EditText
        val cd1 = coordenada1.text.toString().toIntOrNull()
        val cd2 = coordenada2.text.toString().toIntOrNull()
        val long = longitud.text.toString().toDoubleOrNull()

        //Compara los valores ingresados para que no estén vacíos
        if(cd1 == null || cd2 == null || long == null){
            mostrarError("Todos los campos deben contener números válidos.")
            return
        }

        //Como la longitud no puede ser 0, muestra mensaje de error
        if(long <= 0){
            mostrarError("La longitud del radio debe ser mayor a cero.")
            return
        }

        //Inicializa el intent y manda datos a la siguiente actividad
        val intent = Intent(this, CalculoActivity::class.java)

        intent.putExtra("coordenada1",cd1)
        intent.putExtra("coordenada2",cd2)
        intent.putExtra("longitud",long)
        startActivity(intent)
    }

    //Método para enviar el mensaje según los datos ingresados en el EditText
    private fun mostrarError(mensaje:String){
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }

}