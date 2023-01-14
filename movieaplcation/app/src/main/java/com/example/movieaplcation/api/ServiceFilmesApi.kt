package com.example.movieaplcation.api

import com.example.movieaplcation.model.FilmeResposta
import com.example.movieaplcation.model.FilmesDetalhes
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceFilmesApi {

    @GET("movie/popular?api_key=${RetrofitHelper.API_KEY}")
    suspend fun getFilmesApi(@Query("page")  pagina :Int ): Response<FilmeResposta>

    @GET("movie/{movie_id}?api_key=${RetrofitHelper.API_KEY}")
    suspend fun recuperarDetalhesFilmes(
        @Path("movie_id")  idFilme :Int ): Response<FilmesDetalhes>

    @GET("search/movie?api_key=${RetrofitHelper.API_KEY}")
    suspend fun recuperarFilmesPesquisa(
        @Query("query")  pesquisa:String,
        @Query("page")  page:Int

    ): Response<FilmeResposta>

}