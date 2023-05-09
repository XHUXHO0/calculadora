package com.example.calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var num1: Double =0.0
    private var num2: Double =0.0
    private var num3: Double =0.0
    private var resultado: Double = 0.0
    private var operacion: Int = 0

    private lateinit var btn_0: Button
    private lateinit var btn_1: Button
    private lateinit var btn_2: Button
    private lateinit var btn_3: Button
    private lateinit var btn_4: Button
    private lateinit var btn_5: Button
    private lateinit var btn_6: Button
    private lateinit var btn_7: Button
    private lateinit var btn_8: Button
    private lateinit var btn_9: Button
    private lateinit var btn_punto: Button

    private lateinit var btn_suma: Button
    private lateinit var btn_resta: Button
    private lateinit var btn_por: Button
    private lateinit var btn_divicion: Button
    private lateinit var btn_gramaje: Button

    private lateinit var btn_limpiar: Button
    private lateinit var btn_ver_lista: Button
    private lateinit var btn_agregar: Button
    private lateinit var btn_igual: Button
    private lateinit var txt_resultado: TextView
    private lateinit var txt_historial: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_0 = findViewById(R.id.btn_0)
        btn_1 = findViewById(R.id.btn_1)
        btn_2 = findViewById(R.id.btn_2)
        btn_3 = findViewById(R.id.btn_3)
        btn_4 = findViewById(R.id.btn_4)
        btn_5 = findViewById(R.id.btn_5)
        btn_6 = findViewById(R.id.btn_6)
        btn_7 = findViewById(R.id.btn_7)
        btn_8 = findViewById(R.id.btn_8)
        btn_9 = findViewById(R.id.btn_9)
        btn_punto = findViewById(R.id.btn_punto)

        btn_suma = findViewById<Button>(R.id.btn_suma)
        btn_resta = findViewById<Button>(R.id.btn_resta)
        btn_por = findViewById<Button>(R.id.btn_por)
        btn_divicion = findViewById<Button>(R.id.btn_divicion)
        btn_gramaje = findViewById<Button>(R.id.btn_gramaje)

        btn_limpiar = findViewById<Button>(R.id.btn_limpiar)
        btn_ver_lista = findViewById<Button>(R.id.btn_ver_lista)
        btn_agregar = findViewById<Button>(R.id.btn_agregar)
        btn_igual = findViewById<Button>(R.id.btn_igual)
        txt_resultado =findViewById<TextView>(R.id.tv_resultado)
        txt_historial =findViewById<TextView>(R.id.tv_historial)

        btn_0.setOnClickListener{numeroPresionado("0")}
        btn_1.setOnClickListener{numeroPresionado("1")}
        btn_2.setOnClickListener{numeroPresionado("2")}
        btn_3.setOnClickListener{numeroPresionado("3")}
        btn_4.setOnClickListener{numeroPresionado("4")}
        btn_5.setOnClickListener{numeroPresionado("5")}
        btn_6.setOnClickListener{numeroPresionado("6")}
        btn_7.setOnClickListener{numeroPresionado("7")}
        btn_8.setOnClickListener{numeroPresionado("8")}
        btn_9.setOnClickListener{numeroPresionado("9")}
        btn_punto.setOnClickListener{numeroPresionado(".")}

        btn_suma.setOnClickListener{operacionPresionado(SUMA)}
        btn_resta.setOnClickListener{operacionPresionado(RESTA)}
        btn_por.setOnClickListener{operacionPresionado(MULTIPLICACION)}
        btn_divicion.setOnClickListener{operacionPresionado(DIVISION)}
        btn_gramaje.setOnClickListener{operacionPresionado(GRAMAJE)}

        btn_limpiar.setOnClickListener{ resetAll()}
        btn_ver_lista.setOnClickListener{ resetCalculadora()}
        btn_agregar.setOnClickListener{ guadar() }
        btn_igual.setOnClickListener{ resolvePressed() }



    }

    private fun numeroPresionado(digito: String){



            if (txt_resultado.text == "0" && digito != ".") {
                txt_resultado.text = digito
            } else {
                if(digito == "." && txt_resultado.text == "0.0"){
                    txt_resultado.text = "0."
                }else{
                    txt_resultado.text = "${txt_resultado.text}$digito"
                }
            }

            if (operacion == NO_HAY_OPERACION) {
                num1 = txt_resultado.text.toString().toDouble()
            } else {
                num2 = txt_resultado.text.toString().toDouble()
            }


    }

    private fun operacionPresionado(operacion: Int){
        this.operacion = operacion
        txt_resultado.text = "0"
    }

    companion object{
        const val SUMA = 1
        const val RESTA = 2
        const val MULTIPLICACION = 3
        const val DIVISION = 4
        const val GRAMAJE = 5
        const val NO_HAY_OPERACION = 0
    }

    private fun resolvePressed(){
        if(num1 != 0.0 && operacion != 0){
            val result = when(operacion) {
                SUMA -> num1 + num2
                RESTA -> num1 - num2
                MULTIPLICACION -> num1 * num2
                DIVISION -> num1 / num2
                GRAMAJE -> num1 * num2 / 1000

                else -> 0
            }

            num1 = result as Double


            val resultados = if("$result".endsWith(".0")) { "$result".replace(".0","") } else { "%.2f".format(result) }

            txt_resultado.text = resultados
        }else{
            val toast = Toast.makeText(this, "Error no hay nada para datos para calcular", Toast.LENGTH_LONG)
            toast.show()
        }



    }

    private fun resetAll(){
        txt_resultado.text = "0"
        num1 = 0.0
        num2 = 0.0
        operacion = NO_HAY_OPERACION
    }

    private fun resetCalculadora(){
        txt_resultado.text = "0"
        txt_historial.text = ""
        num1 = 0.0
        num2 = 0.0
        num3 = 0.0
        resultado = 0.0
        operacion = NO_HAY_OPERACION
    }

    private fun guadar(){
        if(resultado != 0.0){
            num3 = resultado.toString().toDouble() + txt_resultado.text.toString().toDouble()
            resultado = num3
        }else{
            resultado = txt_resultado.text.toString().toDouble()
        }
        txt_historial.text = resultado.toString()
        resetAll()
    }

}