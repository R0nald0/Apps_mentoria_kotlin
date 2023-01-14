package com.example.imcapp

import java.io.Serializable

class Usuario(nome: String ,peso:Double,altura : Double)  : Serializable {
   lateinit var nome : String
    var altura : Double = 0.0
    var peso   : Double = 0.0

    init {
        this.nome =nome
        this.altura = altura
        this.peso = peso
    }



}