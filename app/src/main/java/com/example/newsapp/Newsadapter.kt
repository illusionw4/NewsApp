package com.example.newsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class Newsadapter(private val listener: Newsitemclicked): RecyclerView.Adapter<NewsViewholder>() {

   private val items: ArrayList<Newsmodel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemnews, parent, false)
        val viewholder = NewsViewholder(view)
        view.setOnClickListener {
            listener.onItemClicked(items[viewholder.adapterPosition])
        }

        return viewholder
    }

    override fun onBindViewHolder(holder: NewsViewholder, position: Int) {
        val currentitem = items[position]
        holder.titleview.text = currentitem.title
        holder.authorview.text = currentitem.author
        Glide.with(holder.itemView.context).load(currentitem.imageUrl).into(holder.imageurl)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun upnews(updatedNews: ArrayList<Newsmodel>){
        items.clear()
        items.addAll(updatedNews)

        notifyDataSetChanged()
    }
}




class NewsViewholder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val titleview: TextView = itemView.findViewById(R.id.title)
    val authorview: TextView = itemView.findViewById(R.id.author)
    val imageurl: ImageView = itemView.findViewById(R.id.imagei)
}

interface Newsitemclicked{
        fun onItemClicked(item: Newsmodel)
}