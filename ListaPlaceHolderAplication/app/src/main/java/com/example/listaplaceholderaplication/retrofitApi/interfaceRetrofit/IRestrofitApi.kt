package com.example.listaplaceholderaplication.retrofitApi.interfaceRetrofit

import com.example.listaplaceholderaplication.model.ComentarioPost
import com.example.listaplaceholderaplication.model.Post
import retrofit2.Response

import retrofit2.http.GET
import retrofit2.http.Path

interface IRestrofitApi {

    @GET("posts")
   suspend fun getPosts(): Response<List<Post>>

   @GET("posts/{id}/comments")
   suspend fun getComentsFromPostagem(@Path("id") id:Int) : Response<List<ComentarioPost>>
}