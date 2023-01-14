package com.example.listaplaceholderaplication.retrofitApi

import com.example.listaplaceholderaplication.retrofitApi.interfaceRetrofit.IRestrofitApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitApi {

    companion object {
        var apiJsonPlaceHonder = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(IRestrofitApi::class.java)
    }
}