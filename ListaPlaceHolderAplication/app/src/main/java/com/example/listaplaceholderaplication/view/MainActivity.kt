package com.example.listaplaceholderaplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listaplaceholderaplication.adapter.PostAdapter
import com.example.listaplaceholderaplication.databinding.ActivityMainBinding
import com.example.listaplaceholderaplication.model.Post
import com.example.listaplaceholderaplication.retrofitApi.RetrofitApi
import kotlinx.coroutines.*
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val bidding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private  var listPost = mutableListOf<Post>()


    override fun onStart() {
        super.onStart()

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bidding.root)

        GlobalScope.launch {
            getPostagens()
        }
        with(bidding){

        }
    }

    suspend fun  getPostagens(){
         var  response : Response<List<Post>>? = null
          try {
               response = RetrofitApi.apiJsonPlaceHonder.getPosts()
          }catch (e : RuntimeException){
              e.printStackTrace()
          }
        if (  response != null && response.isSuccessful){
            response.body()?.forEach {
                listPost.add(it)
                println(listPost)
            }
              CoroutineScope(Dispatchers.Main).launch {
                  bidding.idRcViewPost.adapter = PostAdapter(listPost)
                  bidding.idRcViewPost.layoutManager =LinearLayoutManager(this@MainActivity)
                  bidding.idRcViewPost.addItemDecoration(DividerItemDecoration(
                      this@MainActivity,LinearLayoutManager.HORIZONTAL
                  ))
              }
        }
    }
}