package com.example.movieaplcation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieaplcation.adapter.FilmesAdapter
import com.example.movieaplcation.api.RetrofitHelper
import com.example.movieaplcation.api.ServiceFilmesApi
import com.example.movieaplcation.databinding.ActivityMainBinding
import com.example.movieaplcation.model.Filme
import com.example.movieaplcation.model.FilmeResposta
import com.example.movieaplcation.model.FilmesDetalhes
import com.squareup.picasso.Picasso
import kotlinx.coroutines.*
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private  val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val  api by lazy {
        RetrofitHelper.api
    }

    private var fIlmesList  = ArrayList<Filme>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        GlobalScope.launch {
            recuperarFilmesPopulares()
        }

    }

    private suspend fun recuperarFilmePesquisa() {
        var resposta : Response<FilmeResposta>?=null
        var pesqisa = "Orphan"
        var pagina = 1

        try {
            val serviceFilme = api.create(ServiceFilmesApi::class.java)
            resposta = serviceFilme.recuperarFilmesPesquisa(pesqisa,pagina)
        }catch (e :Exception){
            e.printStackTrace()
            Log.i("tag", "recuperarFilmesPopulares: ${e.message} ")
        }

        if (resposta !=null && resposta.isSuccessful){
            val filmeResposta = resposta.body()
            val listaFilmes = filmeResposta?.filmes

            listaFilmes?.forEach {
                Log.i("tag", "Filme id: ${it.id} Filme Titolu : - ${it.title} - Filme Img ${it.backdrop_path}")
            }

            Log.i("tag", "Total filmes: ${listaFilmes?.size}")

        }else{
            Log.i("tag", "Erro: ${resposta?.code()} ")
            Log.i("tag", "Erro: ${resposta?.body()} ")
        }
    }

  private suspend fun recuperarFilmesPopulares() {
         var resposta : Response<FilmeResposta>?=null
         val pagina =1

         try {
             val serviceFilme = api.create(ServiceFilmesApi::class.java)
              resposta = serviceFilme.getFilmesApi(pagina)
          }catch (e :Exception){
             e.printStackTrace()
             Log.i("tag", "recuperarFilmesPopulares: ${e.message} ")
         }

      if (resposta !=null && resposta.isSuccessful){
            val filmeResposta = resposta.body()
            val listaFilmes = filmeResposta?.filmes

          listaFilmes?.forEach {
              Log.i("tag", "Total filmes: ${it.id} - ${it.title} - ${it.backdrop_path}")
              fIlmesList.add(it)
          }

          withContext(Dispatchers.Main){
              binding.rcViewFIlmes.adapter = FilmesAdapter(fIlmesList)
              binding.rcViewFIlmes.layoutManager =LinearLayoutManager(this@MainActivity,)
          }

          Log.i("tag", "Total filmes: ${filmeResposta?.total_results}")

      }else{
          Log.i("tag", "Erro: ${resposta?.code()} ")
          Log.i("tag", "Erro: ${resposta?.body()} ")
      }
    }

    private suspend fun recuperarDetalhesFilmes() {
        var resposta : Response<FilmesDetalhes>?=null

        try {
            val serviceFilme = api.create(ServiceFilmesApi::class.java)
            resposta = serviceFilme.recuperarDetalhesFilmes(960704)
        }catch (e :Exception){
            e.printStackTrace()
            Log.i("tag", "recuperarFilmesDetalhedPopulares: ${e.message} ")
        }

        if (resposta !=null && resposta.isSuccessful){
            val filmeDetalhesResposta = resposta.body()
             val titulo = filmeDetalhesResposta?.title
             val path_img = filmeDetalhesResposta?.backdrop_path
            val urlIMG =RetrofitHelper.BASE_URLIMG+"w780"+path_img

             withContext(Dispatchers.Main){
                 Picasso.get()
                     .load(urlIMG)
                     .placeholder(R.drawable.carregando)
             //        .into(binding.idImgFilme)
             }
            Log.i("tag", "Total filmes: $titulo - $urlIMG")

        }else{
            Log.i("tag", "Erro: ${resposta?.code()} ")
            Log.i("tag", "Erro: ${resposta?.body()} ")
        }
    }
}