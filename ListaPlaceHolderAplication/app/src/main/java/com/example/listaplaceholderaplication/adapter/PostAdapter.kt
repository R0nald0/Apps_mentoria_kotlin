package com.example.listaplaceholderaplication.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.listaplaceholderaplication.databinding.ItemRvJsonBinding
import com.example.listaplaceholderaplication.model.Post
import com.example.listaplaceholderaplication.view.PostPageActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostAdapter(
     private  val postList : List<Post>,
) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

   inner  class PostViewHolder(itemView: ItemRvJsonBinding): RecyclerView.ViewHolder(itemView.root){
         private lateinit var bidding : ItemRvJsonBinding

         init {
             bidding = itemView
         }

        fun bind( post : Post){

                  bidding.idPost.text = post.id.toString()
                  bidding.titlePost.text = post.title

                  bidding.titlePost.setOnClickListener {
                      val intent = Intent(it.context,PostPageActivity::class.java)
                      intent.putExtra("post",post)
                      ContextCompat.startActivity(it.context,intent,null)

              }

        }

   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view  = ItemRvJsonBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = postList[position]
        holder.bind(post);

    }

    override fun getItemCount(): Int {
        return postList.size
    }

    private fun chargeList(){
        this.postList
        notifyDataSetChanged()
    }
}
