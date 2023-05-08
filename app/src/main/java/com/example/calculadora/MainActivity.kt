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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_0).setOnClickListener{numeroPresionado("0")}
        findViewById<Button>(R.id.btn_1).setOnClickListener{numeroPresionado("1")}
        findViewById<Button>(R.id.btn_2).setOnClickListener{numeroPresionado("2")}
        findViewById<Button>(R.id.btn_3).setOnClickListener{numeroPresionado("3")}
        findViewById<Button>(R.id.btn_4).setOnClickListener{numeroPresionado("4")}
        findViewById<Button>(R.id.btn_5).setOnClickListener{numeroPresionado("5")}
        findViewById<Button>(R.id.btn_6).setOnClickListener{numeroPresionado("6")}
        findViewById<Button>(R.id.btn_7).setOnClickListener{numeroPresionado("7")}
        findViewById<Button>(R.id.btn_8).setOnClickListener{numeroPresionado("8")}
        findViewById<Button>(R.id.btn_9).setOnClickListener{numeroPresionado("9")}
        findViewById<Button>(R.id.btn_punto).setOnClickListener{numeroPresionado(".")}

        findViewById<Button>(R.id.btn_suma).setOnClickListener{operacionPresionado(SUMA)}
        findViewById<Button>(R.id.btn_resta).setOnClickListener{operacionPresionado(RESTA)}
        findViewById<Button>(R.id.btn_por).setOnClickListener{operacionPresionado(MULTIPLICACION)}
        findViewById<Button>(R.id.btn_divicion).setOnClickListener{operacionPresionado(DIVISION)}
        findViewById<Button>(R.id.btn_gramaje).setOnClickListener{operacionPresionado(GRAMAJE)}

        findViewById<Button>(R.id.btn_limpiar).setOnClickListener{ resetAll()}

        findViewById<Button>(R.id.btn_ver_lista).setOnClickListener{ resetCalculadora()}


        findViewById<Button>(R.id.btn_agregar).setOnClickListener{ guadar() }

        findViewById<Button>(R.id.btn_igual).setOnClickListener{ resolvePressed() }



    }

    private fun numeroPresionado(digito: String){



            if (findViewById<TextView>(R.id.tv_resultado).text == "0" && digito != ".") {
                findViewById<TextView>(R.id.tv_resultado).text = "$digito"
            } else {
                if("$digito" == "."){
                    findViewById<TextView>(R.id.tv_resultado).text = "0."
                }else{
                    findViewById<TextView>(R.id.tv_resultado).text =
                        "${findViewById<TextView>(R.id.tv_resultado).text}$digito"
                }
            }

            if (operacion == NO_HAY_OPERACION) {
                num1 = findViewById<TextView>(R.id.tv_resultado).text.toString().toDouble()
            } else {
                num2 = findViewById<TextView>(R.id.tv_resultado).text.toString().toDouble()
            }


    }

    private fun operacionPresionado(operacion: Int){

        this.operacion = operacion

        findViewById<TextView>(R.id.tv_resultado).text = "0"
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
        if(num1 != 0.0 ){
            val result = when(operacion) {
                SUMA -> num1 + num2
                RESTA -> num1 - num2
                MULTIPLICACION -> num1 * num2
                DIVISION -> num1 / num2
                GRAMAJE -> num1 * num2 / 1000

                else -> 0
            }

            num1 = result as Double


            var resultados = if("$result".endsWith(".0")) { "$result".replace(".0","") } else { "%.2f".format(result) }

            findViewById<TextView>(R.id.tv_resultado).text = resultados
        }else{
            val toast = Toast.makeText(this, "Error no hay nada para datos para calcular", Toast.LENGTH_LONG)
            toast.show()
        }



    }

    private fun resetAll(){
        findViewById<TextView>(R.id.tv_resultado).text = "0"
        num1 = 0.0
        num2 = 0.0
        operacion = NO_HAY_OPERACION
    }

    private fun resetCalculadora(){
        findViewById<TextView>(R.id.tv_resultado).text = "0"
        findViewById<TextView>(R.id.tv_historial).text = ""
        num1 = 0.0
        num2 = 0.0
        num3 = 0.0
        resultado = 0.0
        operacion = NO_HAY_OPERACION
    }

    private fun guadar(){
        if(resultado != 0.0){
            num3 = resultado.toString().toDouble() + findViewById<TextView>(R.id.tv_resultado).text.toString().toDouble()
            resultado = num3
        }else{
            resultado = findViewById<TextView>(R.id.tv_resultado).text.toString().toDouble()
        }
        findViewById<TextView>(R.id.tv_historial).text = resultado.toString()
        resetAll()
    }

}