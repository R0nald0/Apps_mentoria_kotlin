package com.example.listaplaceholderaplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listaplaceholderaplication.databinding.ItemRcvComentBinding
import com.example.listaplaceholderaplication.model.ComentarioPost

class ComentsAdapter(
    var listComent : List<ComentarioPost>
) :RecyclerView.Adapter<ComentsAdapter.ComentsViewHolder>() {
    inner class ComentsViewHolder(item : ItemRcvComentBinding):RecyclerView.ViewHolder(item.root){
       private lateinit var  binding : ItemRcvComentBinding

       init {
           binding = item
       }

        fun bind( coment : ComentarioPost){
            binding.email.text = coment.email
             binding.body.text = coment.body
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComentsViewHolder {
        val  view = ItemRcvComentBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ComentsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ComentsViewHolder, position: Int) {
         var coment = listComent[position]
        holder.bind(coment)
    }

    override fun getItemCount(): Int {
        return listComent.size
    }
}