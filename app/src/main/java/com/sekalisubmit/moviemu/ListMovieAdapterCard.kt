package com.sekalisubmit.moviemu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class ListMovieAdapterCard(
    private val listMovie: ArrayList<Movie>,
    private val onClick: (Movie) -> Unit)
    : RecyclerView.Adapter<ListMovieAdapterCard.ListViewHolder>() {
    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageList: ImageView = itemView.findViewById(R.id.list_image)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_col_movie, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listMovie.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (image) = listMovie[position]
        holder.imageList.setImageResource(image)

        holder.itemView.setOnClickListener {
            onClick(listMovie[position])
        }
    }
}