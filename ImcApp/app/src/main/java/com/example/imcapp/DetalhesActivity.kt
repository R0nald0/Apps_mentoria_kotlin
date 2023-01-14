package com.example.imcapp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class DetalhesActivity : AppCompatActivity() {
    lateinit var  txvInfPeso :TextView
    lateinit var  txvInfAltura :TextView
    lateinit var  txvDiagnostico :TextView
    lateinit var  txvNomeUser :TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes)
        carregarCampos()
        carragarDados()

    }

    fun  calcularImc(usuario: Usuario) {
        var imcResultado = usuario.peso / (usuario.altura * usuario.altura)
        verificaImc(imcResultado)
    }

    fun  calcularImc(peso:Double,altura: Double) {
             var imcResultado = peso / (altura * altura)
              verificaImc(imcResultado)
      }

    fun verificaImc(imc: Double){
        when(imc){
            in 0.0..18.5 -> {
                txvDiagnostico.setTextColor(Color.parseColor("#9B0215"))
                txvDiagnostico.text = classificacaoImc.MAGREZA.toString()}
            in 18.6..24.9->{
                txvDiagnostico.setTextColor(Color.parseColor("#0397EC"))
                txvDiagnostico.text  = classificacaoImc.NORMAL.toString()}
            in 25.0..29.9->{
                txvDiagnostico.setTextColor(Color.parseColor("#FC2222"))
                txvDiagnostico.text  = classificacaoImc.SOBREPESO.toString()}
            in 30.0..39.9->{
                txvDiagnostico.setTextColor(Color.parseColor("#EA2E05"))
                txvDiagnostico.text  = classificacaoImc.OBESIDADE.toString()}
            in 40.0..1000.0  ->{
                txvDiagnostico.setTextColor(Color.parseColor("#A32307"))
                txvDiagnostico.text  = classificacaoImc.OBESIDADE_GRAVE.toString()
            }
        }
    }

    fun carragarDados(){
          val extra = intent.extras
          val  peso = extra?.getDouble("peso")
          val  altura = extra?.getDouble("altura")

          //txvInfPeso.text =  " Peso Informada: $peso "
         // txvInfAltura.text = "Altura Informada: $altura"
         var usuario = extra?.getSerializable("usuario") as Usuario

         txvNomeUser.text = usuario.nome
         txvInfPeso.text =  " Peso Informada: ${usuario.peso} "
         txvInfAltura.text = "Altura Informada: ${usuario.altura}"
         if (peso != null && altura != null) {
             calcularImc(usuario)
         }
     }

    fun carregarCampos(){
        txvInfPeso =findViewById(R.id.idTxvPeso)
        txvInfAltura =findViewById(R.id.idTxvAltura)
        txvDiagnostico =findViewById(R.id.idTxvDiagnostico)
        txvNomeUser =findViewById(R.id.idTxvNome)
    }
}
