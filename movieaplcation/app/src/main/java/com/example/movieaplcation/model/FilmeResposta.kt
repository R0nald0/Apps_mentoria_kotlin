package com.example.movieaplcation.model

import com.google.gson.annotations.SerializedName

data class FilmeResposta(
    @SerializedName("results")
    val filmes: List<Filme>,
    @SerializedName("total_results")
    val total_results: Int,
    @SerializedName("pages")
    val page: Int,
    @SerializedName("Total_pages")
    val total_pages: Int,

)