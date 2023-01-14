package com.example.movieaplcation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieaplcation.api.RetrofitHelper
import com.example.movieaplcation.databinding.ItemRcviewLayoutBinding
import com.example.movieaplcation.model.Filme
import com.squareup.picasso.Picasso

class FilmesAdapter(
    val listFIlmes : ArrayList<Filme>
): RecyclerView.Adapter<FilmesAdapter.FilmesViewHolder>() {

    inner class FilmesViewHolder(itemView:ItemRcviewLayoutBinding ): RecyclerView.ViewHolder(itemView.root){
        private var  binding :ItemRcviewLayoutBinding

         init {
             binding =itemView
         }
        fun bind(filme: Filme){
            binding.idFilmeTitulo.text =filme.title
            val imgUrl =RetrofitHelper.BASE_URLIMG+RetrofitHelper.BASE_URLIMG_SIZE+filme.backdrop_path
            Picasso.get().load(imgUrl).into(binding.idFilmeImagem)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmesViewHolder {
         val view = ItemRcviewLayoutBinding
             .inflate(LayoutInflater.from(parent.context),parent,false)
          return  FilmesViewHolder(view)
           }

    override fun onBindViewHolder(holder: FilmesViewHolder, position: Int) {
         var filme = listFIlmes[position]
        holder.bind(filme)
    }

    override fun getItemCount(): Int {
        return  listFIlmes.size
    }
}