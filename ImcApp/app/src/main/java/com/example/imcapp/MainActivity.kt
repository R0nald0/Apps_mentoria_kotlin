package com.example.imcapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    lateinit var  edtPeso:EditText
    lateinit var  edtAltura :EditText
    lateinit var  btnCalcaular: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        carregarCampos()

    }

    fun passarDados(view: View){

         val peso =  edtPeso.text.toString().toDouble()
         val altura =  edtAltura.text.toString().toDouble()

         val usuario = Usuario("nome", peso,altura)


        Log.i("numero" , peso.toString())
        val intent = Intent(this,DetalhesActivity::class.java);
       // intent.putExtra("peso",edtPeso.text)
       // intent.putExtra("altura",edtAltura.text)
        intent.putExtra("usuario",usuario)

        chamarOutraTela(intent)
    }
    fun chamarOutraTela(intent: Intent){
            startActivity(intent)
    }
    fun carregarCampos(){
        edtPeso= findViewById(R.id.idEdtPeso)
        edtAltura =findViewById(R.id.idEdtAltura)
        btnCalcaular =findViewById(R.id.idBtn)
    }
}