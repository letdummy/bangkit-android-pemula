package com.sekalisubmit.moviemu

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(){
    private lateinit var rvMovie: RecyclerView
    private val list = ArrayList<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCard: ImageButton = findViewById(R.id.btn_view_card)
        var isLinear = true

        rvMovie = findViewById(R.id.rv_movie)
        rvMovie.setHasFixedSize(true)

        list.addAll(getListMovies())
        showRecyclerList()

        btnCard.setOnClickListener {
            when (isLinear) {
                true -> {
                    rvMovie.layoutManager = GridLayoutManager(this, 3)
                    val listMovieAdapterCard = ListMovieAdapterCard(list)
                    rvMovie.adapter = listMovieAdapterCard

                    btnCard.setImageResource(R.drawable.view_list)
                    isLinear = false
                }
                false -> {
                    rvMovie.layoutManager = LinearLayoutManager(this)
                    val listMovieAdapter = ListMovieAdapter(list)
                    rvMovie.adapter = listMovieAdapter

                    btnCard.setImageResource(R.drawable.view_card)
                    isLinear = true
                }
            }
        }
    }

    private fun getListMovies(): ArrayList<Movie> {
        val imageData = resources.obtainTypedArray(R.array.data_image)
        val titleData = resources.getStringArray(R.array.data_title)
        val overviewData = resources.getStringArray(R.array.data_overview)
        val listMovie = ArrayList<Movie>()
        for (i in titleData.indices) {
            val movie = Movie(imageData.getResourceId(i, -1), titleData[i], overviewData[i])
            listMovie.add(movie)
        }
        return listMovie
    }

    private fun showRecyclerList() {
        rvMovie.layoutManager = LinearLayoutManager(this)
        val listMovieAdapter = ListMovieAdapter(list)
        rvMovie.adapter = listMovieAdapter
    }
}