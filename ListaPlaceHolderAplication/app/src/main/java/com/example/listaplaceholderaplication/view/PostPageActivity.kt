package com.example.listaplaceholderaplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listaplaceholderaplication.R
import com.example.listaplaceholderaplication.adapter.ComentsAdapter
import com.example.listaplaceholderaplication.databinding.ActivityPostPageBinding
import com.example.listaplaceholderaplication.model.ComentarioPost
import com.example.listaplaceholderaplication.model.Post
import com.example.listaplaceholderaplication.retrofitApi.RetrofitApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.http.POST

class PostPageActivity : AppCompatActivity() {

     private val bidding by lazy {
         ActivityPostPageBinding.inflate(layoutInflater)
     }
     private  var listComents = mutableListOf<ComentarioPost>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bidding.root)

        val post = getExtras();
        with(bidding){
              if (post != null){
                  GlobalScope.launch {
                      bidding.id.text = post.id.toString()
                      bidding.titulo.text ="- ${post.title}"
                      getComents(post.id)
                  }
              }
        }
    }



    suspend fun getComents(id:Int){
         var response : Response<List<ComentarioPost>>? = null
        try {
            response = RetrofitApi.apiJsonPlaceHonder.getComentsFromPostagem(id)
        }catch (e:RuntimeException){
            e.printStackTrace()
        }
        if (response !=null && response.isSuccessful){
              response.body()?.forEach {
                   listComents.add(it)
                  println(listComents)
              }
            CoroutineScope(Dispatchers.Main).launch{
                bidding.rcvComents.adapter = ComentsAdapter(listComents)
                bidding.rcvComents.layoutManager = LinearLayoutManager(this@PostPageActivity)
            }

        }
    }
    fun getExtras() : Post? {
        val dados =  intent.extras
        val post =  dados?.getParcelable<Post>("post")
              if (post!= null) return post

       return null
    }
}