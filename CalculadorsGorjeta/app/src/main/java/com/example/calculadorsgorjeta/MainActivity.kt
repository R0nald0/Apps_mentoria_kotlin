package com.example.calculadorsgorjeta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.view.isInvisible
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

   private lateinit var edtValor : EditText
   private lateinit var edtPorcentagem : EditText
   private lateinit var btnCalcular : Button
   private lateinit var txvGorjeta : TextView
   private lateinit var txvTotal  : TextView
   private lateinit var txvPessoa : TextView
   private lateinit var  ckDividirConta: CheckBox
   private lateinit var QtdPessoas : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getViws()

        ckDividirConta.setOnCheckedChangeListener { _,isChecked->
            if (isChecked){
                QtdPessoas.visibility = View.VISIBLE
            } else{
                QtdPessoas.visibility = View.INVISIBLE
            }

        }
      btnCalcular.setOnClickListener {


           if (!validarCampos()){
               Toast.makeText(this,"Preencha os campos corretamente",Toast.LENGTH_LONG).show()
           }
            val precoConta = edtValor.text.toString().toDouble()
            val valorPorcentagem = edtPorcentagem.text.toString().toDouble()
            val qtddPessoa = QtdPessoas.text.toString().toInt();

          calcular(precoConta,valorPorcentagem)
      }

    }

    private fun validarCampos(): Boolean {
         if(ckDividirConta.isChecked){
               if (QtdPessoas.text.isEmpty()){
                    Toast.makeText(this,"Preencha a quantidade de Pessoas corretamente",Toast.LENGTH_LONG).show()
                return false
               }
         }
         if (edtValor.text.toString().isEmpty() || edtPorcentagem.text.toString().isEmpty()) return false
        return true
    }
    private fun calcular( precoConta :Double ,porcentagem:Double, qtdPessoas:Int = 1){

        val gorjeta = precoConta *(porcentagem /100)

        txvGorjeta.text =" valor Gorjeta :${formatarNumero(gorjeta)}"
          val total= precoConta+ gorjeta
        txvTotal.text = "Valor total: ${formatarNumero(total)}"

    val  qtdPessoas = if (ckDividirConta.isChecked){
            QtdPessoas.text.toString().toInt()
        }else{
            1
        }

        val totalPesso= total/qtdPessoas
        txvPessoa.text ="valor por pessoar ${formatarNumero(totalPesso)}"

    }

    fun formatarNumero(numero:Double):String{
        val decimalFormat = DecimalFormat("R$.00")
         return decimalFormat.format(numero)
    }

    fun getViws (){
         edtValor = findViewById(R.id.idEdtValorConta)
        edtPorcentagem = findViewById(R.id.idEdtPorcentagemGorjeta)
        btnCalcular = findViewById(R.id.idBtnCalcular)
        txvGorjeta = findViewById(R.id.idTxvGorjeta)
        txvPessoa = findViewById(R.id.idTxvPessoa)
         txvTotal = findViewById(R.id.idTxvTotal)
         ckDividirConta = findViewById(R.id.idCheckDividiv)
         QtdPessoas = findViewById(R.id.idQtdPessoas)
    }
}