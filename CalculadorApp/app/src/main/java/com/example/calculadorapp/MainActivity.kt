package com.example.calculadorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private  lateinit var txvResultado : TextView
    private lateinit var txvNumber2 :TextView
    private  lateinit var btnAC :Button
    private  lateinit var btnSoma :Button
    private  lateinit var btnDiv :Button
    private  lateinit var btnSub :Button
    private  lateinit var btnMult :Button
    private  lateinit var btn1 :Button
    private  lateinit var btn2 :Button
    private  lateinit var btn3 :Button
    private  lateinit var btn4 :Button
    private  lateinit var btn5 :Button
    private  lateinit var btn6 :Button
    private  lateinit var btn7 :Button
    private  lateinit var btn8 :Button
    private  lateinit var btn9 :Button
    private  lateinit var btn0 :Button
    private  lateinit var btnIgual :Button

    private var valor1= arrayListOf<Double>()
    private var valor =""
    var valor2 =0.0
    var valor3 =0.0
    var idBtnClicado = 0
    var btnClicado = false
    var sinal = ""
    var resultado = 0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getView()

       btnAC.setOnClickListener {

             limparCampos()
             resultado = 0.0
             txvResultado.text ="0"
        }

         btnIgual.setOnClickListener {
             when(idBtnClicado){
                 btnSoma.id -> { calcular(valor2,valor3,::soma) }
                 btnMult.id -> {calcular(valor2,valor3,::multiplicar)}
                 btnSub.id -> {calcular(valor2,valor3,::subtrair)}
                 btnDiv.id -> {calcular(valor2,valor3,::dividir)}
             }
             valor2= resultado
              limparCampos()
         }
    }

    fun calcular(number1:Double,number2:Double,funcao:(Double,Double)->Double){
          resultado =funcao(number1, number2)
        txvResultado.text = resultado.toString()
    }

    fun soma( numero1 : Double ,numero2:Double):Double{
          return numero1 + numero2
    }
    fun multiplicar( numero1 :Double ,numero2: Double):Double{
          return numero1 * numero2
    }
    fun subtrair( numero1 : Double ,numero2: Double):Double{

          return numero1 - numero2
    }
    fun dividir( numero1 : Double ,numero2: Double):Double{

          return numero1 / numero2
    }

    fun clickBotao(view: View){
        when(view.id){
          btn0.id ->valor+="0"
          btn1.id ->valor+="1"
          btn2.id ->valor+="2"
          btn3.id ->valor+="3"
          btn4.id ->valor+="4"
          btn5.id ->valor+="5"
          btn6.id ->valor+="6"
          btn7.id ->{valor+="7"}
          btn8.id ->{valor+="8"}
          btn9.id ->{valor+="9"}
        }

       // valor1.add(valor.toDouble())

        if (btnClicado){
            valor3 = valor.toDouble()
            txvNumber2.visibility =View.VISIBLE
            txvNumber2.text = "${valor2.toInt()} ${sinal} ${valor3.toInt()}"
        }else{
            valor2 = valor.toDouble()
            txvNumber2.text = "${valor2.toInt() }"
            txvNumber2.visibility = View.VISIBLE
        }
    }

    fun clickBotaoFuncao(view: View){
        when(view.id){
            btnSoma.id -> {//txvSinal.text = "+"
                sinal="+"
                txvNumber2.text = "${valor2.toInt()} ${sinal}"
                idBtnClicado = view.id
                btnClicado = !btnClicado
                valor=""
            }
            btnMult.id -> {
                sinal="*"
                txvNumber2.text = "${valor2.toInt()} ${sinal}"
                idBtnClicado =view.id
                btnClicado =!btnClicado
                valor=""
            }
            btnSub.id -> {
                sinal="-"
                txvNumber2.text = "${valor2.toInt()} ${sinal}"
                idBtnClicado =view.id
                btnClicado =!btnClicado
                valor=""
            }
            btnDiv.id -> {
                btnClicado =!btnClicado
                sinal="/"
                txvNumber2.text = "${valor2.toInt()} ${sinal}"
                idBtnClicado = view.id
                valor=""
            }
       }
    }

    fun limparCampos(){
        valor =""
        btnClicado=false
        valor3=0.0
        txvNumber2.visibility = View.INVISIBLE
    }

    fun  getView(){
        txvResultado= findViewById(R.id.idTxvResultado)

        txvNumber2= findViewById(R.id.idTxvNumber2)
        btnAC= findViewById(R.id.idBtnAc)
        btnSoma= findViewById(R.id.idBtnSoma)
        btnDiv= findViewById(R.id.idBtnDiv)
        btnSub= findViewById(R.id.idBtnSub)
        btnMult= findViewById(R.id.idBtnMult)
        btn0= findViewById(R.id.idBtn0)
        btn1= findViewById(R.id.idBtn1)
        btn2= findViewById(R.id.idBtn2)
        btn3= findViewById(R.id.idBtn3)
        btn4= findViewById(R.id.idBtn4)
        btn5= findViewById(R.id.idBtn5)
        btn6= findViewById(R.id.idBtn6)
        btn7= findViewById(R.id.idBtn7)
        btn8= findViewById(R.id.idBtn8)
        btn9= findViewById(R.id.idBtn9)
        btnIgual =findViewById(R.id.idBtnIgual)

    }
}