package com.aint.fichasinstrumentos_es1921013978_annt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Inicializa los botones y los ubica por su ID
        val botonViento: ImageButton = findViewById(R.id.botonViento)
        val botonCuerdas: ImageButton = findViewById(R.id.botonCuerdas)
        val botonPercusion: ImageButton = findViewById(R.id.botonPercusion)
        val botonElectricos: ImageButton = findViewById(R.id.botonElectricos)

        //Indica la acción que realizará cada botón
        botonViento.setOnClickListener{
            val intent = Intent(this@MainActivity,InstrumentosActivity::class.java)
            intent.putExtra("categoria", "viento") //Lanza el botón con los parámetros configurados para categoría e indica el valor viento
            startActivity(intent) //Inicializa la actividad considerando el valor viento
        }

        botonCuerdas.setOnClickListener{
            val intent = Intent(this@MainActivity,InstrumentosActivity::class.java)
            intent.putExtra("categoria", "cuerdas")
            startActivity(intent)
        }

        botonPercusion.setOnClickListener{
            val intent = Intent(this@MainActivity,InstrumentosActivity::class.java)
            intent.putExtra("categoria", "percusion")
            startActivity(intent)
        }

        botonElectricos.setOnClickListener{
            val intent = Intent(this@MainActivity,InstrumentosActivity::class.java)
            intent.putExtra("categoria", "electricos")
            startActivity(intent)
        }
    }
}