package com.aint.fichasinstrumentos_es1921013978_annt

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.widget.*

class InstrumentosActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    //Inicializa los componentes a utilizar en la Activity
    private lateinit var spinnerInstrumentos: Spinner
    private lateinit var imagenInstrumento: ImageView
    private lateinit var tvDescripcion:TextView
    private lateinit var tvCaracteristicas: TextView
    private lateinit var tvResena: TextView
    private lateinit var cbCasilladeVerificacion: CheckBox
    private lateinit var sharedPreferences: SharedPreferences

    //Inicializa los arreglos para el llenado del spinner
    private val instrumentosViento = arrayOf("saxofon","flauta","clarinete","trompeta","oboe")
    private val instrumentosCuerda = arrayOf("guitarra","arpa","violin","piano")
    private val instrumentosPercusion = arrayOf("timbal","tambor","platillos","bombo")
    private val instrumentosElectricos = arrayOf("bajo_electrico","guitarra_electrica","theremin","sintetizador")

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_instrumentos)

            //Ubica los elementos de la interfaz por su ID
            spinnerInstrumentos=findViewById(R.id.spinnerInstrumentos)
            imagenInstrumento=findViewById(R.id.imagenInstrumento)
            tvDescripcion=findViewById(R.id.tvdescripcion)
            tvCaracteristicas=findViewById(R.id.tvcaracteristicas)
            tvResena=findViewById(R.id.tvresena)
            cbCasilladeVerificacion=findViewById(R.id.cbCasilladeverificacion)
            sharedPreferences=getSharedPreferences("MisPreferencias", MODE_PRIVATE) //Inicializa una clave-valor para el guardado de dato
            //con un identificador, para guardar los instrumentos revisados

            cbCasilladeVerificacion.setOnCheckedChangeListener { _, isChecked -> //Mantiene a la escucha la casilla verficadora
                val instrumentoSeleccionado = spinnerInstrumentos.selectedItem.toString() //Al seleccionar un instrumento del spinner
                val revisados = sharedPreferences.getStringSet("instrumentos_revisados", HashSet<String>())
                val nuevosRevisados = revisados?.toMutableSet() //revisa sí el elemento está marcado previamente

                if (isChecked){
                    nuevosRevisados?.add(instrumentoSeleccionado) //Sí está marcado, se añade a instrumento seleccionado
                } else {
                    nuevosRevisados?.remove(instrumentoSeleccionado) //si no, se quita de instrumento seleccionado
                }
                sharedPreferences.edit().putStringSet("instrumentos_revisados",nuevosRevisados).apply() //Edita los elementos marcados
                //y los añade a las preferencias
            }

            val instrumentos: Array<String> = when(intent.getStringExtra("categoria")){ //Define la clasificación de instrumentos para
                "viento"-> instrumentosViento //el spinner, y selecciona la cadena o string-array
                "cuerdas"->instrumentosCuerda //que contiene los instrumentos de cada clasificación
                "percusion"->instrumentosPercusion
                "electricos"->instrumentosElectricos
                else-> arrayOf() //Si no se selecciona ninguno, no muestra datos
            }

            val adapter= ArrayAdapter(this,android.R.layout.simple_spinner_item,instrumentos) //Se declara el adaptador
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) //asigna el tipo de spinner que desplegará
            spinnerInstrumentos.adapter = adapter
            spinnerInstrumentos.onItemSelectedListener = this

            val instrumentoSeleccionado = spinnerInstrumentos.selectedItem.toString() //según el instrumento que se seleccione
            val revisados = sharedPreferences.getStringSet("instrumentos_revisados",HashSet<String>()) //se revisarám las propiedades
            cbCasilladeVerificacion.isChecked = revisados?.contains(instrumentoSeleccionado) ?: false //verifica la casilla

            val botonRegresar:Button = findViewById(R.id.botonregresar) //declara el botón para regresar
            botonRegresar.setOnClickListener{
                val intent = Intent(this, MainActivity::class.java) //devuelve a mainactivity
                startActivity(intent)
                finish()
            }

            val botonHistorico: Button = findViewById(R.id.botonHistorico) //Declara botón para el histórico
            botonHistorico.setOnClickListener{
                val intent = Intent(this,HistoricoActivity::class.java)
                intent.putExtra("instrumento_seleccionado",instrumentoSeleccionado) //Redirecciona a historico activity
                startActivity(intent) //Envía parámetros de los instrumentos que se han seleccionado
            }
        }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) { //Según el instrumento seleccionado
        val instrumentoSeleccionado = parent?.getItemAtPosition(position).toString() //Determina la posición del instrumento
        val imagenID = resources.getIdentifier(instrumentoSeleccionado,"drawable",packageName) //retoma la imagen que tiene el mismo nombre
        val descripcionID = resources.getIdentifier("descripcion$instrumentoSeleccionado","string",packageName) //Retoma los valores
        val caracteristicasID = resources.getIdentifier("caract$instrumentoSeleccionado","string",packageName) //de string
        val resenaID = resources.getIdentifier("resena$instrumentoSeleccionado","string",packageName) //según su nombre

        if (imagenID!=0){ //Sí los datos son diferentes a 0
            imagenInstrumento.setImageResource(imagenID) //entonces establece la imagen que corresponde con el identificador
        }
        if (descripcionID !=0){
            tvDescripcion.text=getString(descripcionID)
        }
        if (caracteristicasID!=0){
            tvCaracteristicas.text=getString(caracteristicasID)
        }
        if (resenaID!=0){
            tvResena.text = getString(resenaID)
        }

        val revisados = sharedPreferences.getStringSet("instrumentos_revisados",HashSet<String>())
        cbCasilladeVerificacion.isChecked = revisados?.contains(instrumentoSeleccionado) ?: false //Valida las casillas marcadas y actualiza las
    }                                                                                             //preferencias

    override fun onNothingSelected(parent: AdapterView<*>?) {
        //No se ejectuan acciones cuando no se ha seleccionado ningún objeto del spinner
        //Esto por qué siempre hay un objeto seleccionado
    }
}

