package com.aint.ecuacioncircunferencia_es1921013978_annt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlin.math.abs
import kotlin.math.pow

class CalculoActivity : AppCompatActivity() {

    private lateinit var ecuacionTV: TextView
    private lateinit var sustitucionTV: TextView
    private lateinit var resultadosTV: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculo)

        ecuacionTV = findViewById(R.id.ecuacion)
        sustitucionTV = findViewById(R.id.sustitucion)
        resultadosTV=findViewById(R.id.resultados)

        val coordenada1 = intent.getIntExtra("coordenada1",0) //Recibe parámetros de la actividad anterior
        val coordenada2 = intent.getIntExtra("coordenada2",0)
        val longitud = intent.getDoubleExtra("longitud",0.0)


        //Se declaran las variables a utilizar
        val ecuacion: String
        val sustitucion: String
        val resultado: String
        val r2:Double
        val ax:Int
        val by:Int
        val a2:Int
        val b2:Int
        val f:Double
        val coordenada1absoluto = abs(coordenada1)
        val coordenada2absoluto = abs(coordenada2)

        //Realiza cálculo de operaciones
        r2 = longitud*longitud
        ax = coordenada1*2
        val axabs = abs(ax)
        a2 = coordenada1*coordenada1
        by = coordenada2*2
        val byabs = abs(by)
        b2 = coordenada2*coordenada2
        f = a2+b2-r2

        //Sí cualquiera de las coordenadas es diferente a 0, utiliza la ecuación mencionada
        if(coordenada1 != 0 || coordenada2 != 0){
            ecuacion = "Ecuación: (x-a)²+(y-b)²=r²"
            if(coordenada1 > 0 && coordenada2 > 0){ //Estos if solo cambian la sustitución dependiendo el valor de las coordenadas
                //Para evitar que aparezcan símbolos como +- o --
                sustitucion = "Sustitución: (x-${coordenada1})²+(y-${coordenada2})²=${longitud}² \n" +
                        " (-${coordenada1}x)²+(-${coordenada2}y)²=${longitud}² \n" +
                        " x²-${ax}x+${a2}+y²-${by}y+${b2}-${r2}=0 \n" +
                        " x²+y²-${ax}x-${by}y+${f} = 0"
                resultado = "El resultado es: x²+y²-${ax}x-${by}y+${f} = 0"
            } else if (coordenada1 > 0 && coordenada2 < 0) {
                sustitucion = "Sustitución: (x-${coordenada1})²+(y-${coordenada2})²=${longitud}² \n" +
                        " (-${coordenada1}x)²+(${coordenada2absoluto}y)²=${longitud}² \n" +
                        " x²-${ax}x+${a2}+y²+${byabs}y+${b2}-${r2}=0 \n" +
                        " x²+y²-${ax}x+${byabs}y+${f} = 0"
                resultado = "El resultado es: x²+y²-${ax}x+${byabs}y+${f} = 0"
            } else if (coordenada1 < 0 && coordenada2 > 0){
                sustitucion = "Sustitución: (x-${coordenada1})²+(y-${coordenada2})²=${longitud}² \n" +
                        " (${coordenada1absoluto}x)²+(-${coordenada2}y)²=${longitud}² \n" +
                        " x²+${axabs}x+${a2}+y²-${by}y+${b2}-${r2}=0 \n" +
                        " x²+y²+${axabs}x-${by}y+${f} = 0"
                resultado = "El resultado es: x²+y²+${axabs}x-${by}y+${f} = 0"
            } else {
                sustitucion = "Sustitución: (x-${coordenada1})²+(y-${coordenada2})²=${longitud}² \n" +
                        " (${coordenada1absoluto}x)²+(${coordenada2absoluto}y)²=${longitud}² \n" +
                        " x²+${axabs}x+${a2}+y²+${byabs}y+${b2}-${r2}=0 \n" +
                        " x²+y²+${axabs}x+${byabs}y+${f} = 0"
                resultado = "El resultado es: x²+y²+${axabs}x+${byabs}y+${f} = 0"
            }
        } else { //Sí las coordenadas son 0 en ambos casos entoces la ecuación es otra
            ecuacion = "Ecuación: x²+y² = r²"
            sustitucion = "Sustitución: x²+y² = ${r2}"
            resultado = "El resultado es: x²+y² = ${r2}"
        }

        ecuacionTV.text = ecuacion //sustituye los valores de los textview por la coincidencia encontrada
        sustitucionTV.text = sustitucion
        resultadosTV.text = resultado

        val botonRegresar: Button = findViewById(R.id.regresar) //Botón para regresar al menú anterior
        botonRegresar.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}