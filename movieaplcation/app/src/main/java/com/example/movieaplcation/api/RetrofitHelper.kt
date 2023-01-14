package com.example.movieaplcation.api
import  retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitHelper {

    companion object {
          const val API_KEY ="46bab2f382fc37a6fd305e26d885ba46"
          const val BASE_URL ="https://api.themoviedb.org/3/"
          const val BASE_URLIMG ="https://image.tmdb.org/t/p/"
          const val BASE_URLIMG_SIZE ="w780"


        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
}