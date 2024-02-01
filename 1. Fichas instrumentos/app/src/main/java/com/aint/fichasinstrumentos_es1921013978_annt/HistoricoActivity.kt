package com.aint.fichasinstrumentos_es1921013978_annt

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HistoricoActivity : AppCompatActivity() {

    //declara objetos de la interfaz
    private lateinit var lvEstudiados:ListView
    private lateinit var lvPendientes:ListView
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historico)

        //identifica los objetos de la activity
        lvEstudiados=findViewById(R.id.lvestudiados)
        lvPendientes=findViewById(R.id.lvpendientes)
        sharedPreferences = getSharedPreferences("MisPreferencias", MODE_PRIVATE) //retoma las preferencias almacenadas

        //Obtiene los elementos marcados
        val instrumentosEstudiados=sharedPreferences.getStringSet("instrumentos_revisados",HashSet<String>())

        val instrumentos = resources.getStringArray(R.array.instrumentos) //Almacena el string array con todos los instrumentos
        val instrumentosPendientes = getInstrumentosPendientes(instrumentos,instrumentosEstudiados) //determina los instrumentos pendientes
        //comparando los instrumentos marcados de las preferencias

        //Crea los adaptadores para los ListView y les integra la información de los instrumentos
        val adapterEstudiados = ArrayAdapter(this,android.R.layout.simple_list_item_1,instrumentosEstudiados?.toList() ?: listOf())
        val adapterPendientes = ArrayAdapter(this,android.R.layout.simple_list_item_1,instrumentosPendientes)
        lvEstudiados.adapter = adapterEstudiados
        lvPendientes.adapter = adapterPendientes

        //Botón regresar, devuelve a MainActivity
        val botonRegresar: Button = findViewById(R.id.botonregresar)
        botonRegresar.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    //método para obtener instrumentos pendientes de estudiar
    private fun getInstrumentosPendientes (instrumentos:Array<String>,instrumentosEstudiados: Set<String>?): List<String>{
        val instrumentosPendientes = ArrayList<String>() //define el arreglo
        if(instrumentosEstudiados != null){ //Si los instrumentos estudiados no estan vacíos
            for (instrumento in instrumentos){ //bucle para añadir cada instrumento a los instrumentos pendientes luego de compararlos
                if (!instrumentosEstudiados.contains(instrumento)){ //sí los instrumentos estudiados no contiene uno de los isntrumentos
                    instrumentosPendientes.add(instrumento) //añade instrumento a la lista de instrumentos pendientes
                }
            }
        }else{ //si los instrumentos estudiados están vacíos
            instrumentosPendientes.addAll(instrumentos) //añade todos los instrumentos a instrumentos pendientes
        }
        return instrumentosPendientes //Al final devuelve los instrumentos pendientes
    }
}