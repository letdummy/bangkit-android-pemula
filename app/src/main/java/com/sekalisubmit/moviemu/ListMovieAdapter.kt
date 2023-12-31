package com.sekalisubmit.moviemu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListMovieAdapter(
    private val listMovie: ArrayList<Movie>,
    private val onClick: (Movie) -> Unit)
    : RecyclerView.Adapter<ListMovieAdapter.ListViewHolder>() {
    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageList: ImageView = itemView.findViewById(R.id.list_image)
        val titleList: TextView = itemView.findViewById(R.id.list_title)
        val overviewList: TextView = itemView.findViewById(R.id.list_overview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_movie, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listMovie.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (image, title, overview) = listMovie[position]
        holder.imageList.setImageResource(image)
        holder.titleList.text = title

        val regex = Regex("(?<=\\n\n).{0,100}")
        val limitedOverview = regex.find(overview)?.value.toString()

        holder.overviewList.text = limitedOverview
        holder.itemView.setOnClickListener {
            onClick(listMovie[position])
        }
    }
}