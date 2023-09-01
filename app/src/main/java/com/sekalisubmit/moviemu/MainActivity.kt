package com.sekalisubmit.moviemu

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    private lateinit var rvMovie: RecyclerView
    private val list = ArrayList<Movie>()

    var isLinear = true

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCard: ImageButton = findViewById(R.id.btn_view_card)

        val btnProfile: ImageButton = findViewById(R.id.btn_profile)

        if (savedInstanceState != null) {
            isLinear = savedInstanceState.getBoolean("isLinear", true)
        }

        rvMovie = findViewById(R.id.rv_movie)
        rvMovie.setHasFixedSize(true)

        list.addAll(getListMovies())
        showRecyclerList()

        btnCard.setOnClickListener {
            when (isLinear) {
                true -> {
                    if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                        rvMovie.layoutManager = GridLayoutManager(this, 5)
                    } else {
                        rvMovie.layoutManager = GridLayoutManager(this, 3)
                    }
                    val listMovieAdapterCard = ListMovieAdapterCard(list, onClick = { rvClick(it) })
                    rvMovie.adapter = listMovieAdapterCard

                    btnCard.setImageResource(R.drawable.view_list)
                    isLinear = false
                }
                false -> {
                    rvMovie.layoutManager = LinearLayoutManager(this)
                    val listMovieAdapter = ListMovieAdapter(list, onClick = { rvClick(it) })
                    rvMovie.adapter = listMovieAdapter

                    btnCard.setImageResource(R.drawable.view_card)
                    isLinear = true
                }
            }
        }

        btnProfile.setOnClickListener {
            val moveIntent = Intent(this@MainActivity, ProfilePage::class.java)
            startActivity(moveIntent)
        }
    }

    @SuppressLint("Recycle")
    private fun getListMovies(): ArrayList<Movie> {
        val imageData = resources.obtainTypedArray(R.array.data_image)
        val titleData = resources.getStringArray(R.array.data_title)
        val overviewData = resources.getStringArray(R.array.data_overview)
        val listMovie = ArrayList<Movie>()
        for (i in titleData.indices) {
            val movie = Movie(imageData.getResourceId(i, -1),
                titleData[i], overviewData[i])
            listMovie.add(movie)
        }
        return listMovie
    }

    private fun showRecyclerList() {
        // dicek status isLinear untuk menjaga bentuk rv ketika user kembali ke home
        when (isLinear){
            true -> {
                rvMovie.layoutManager = LinearLayoutManager(this)
                val listMovieAdapter = ListMovieAdapter(list, onClick = { rvClick(it) })
                rvMovie.adapter = listMovieAdapter
            }
            false -> {
                if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    rvMovie.layoutManager = GridLayoutManager(this, 5)
                } else {
                    rvMovie.layoutManager = GridLayoutManager(this, 3)
                }
                val listMovieAdapterCard = ListMovieAdapterCard(list, onClick = { rvClick(it) })
                rvMovie.adapter = listMovieAdapterCard
            }
        }
    }

    private fun rvClick(it: Movie) {
        val moveIntent = Intent(this@MainActivity, DetailPage::class.java)
        moveIntent.putExtra("movie_data", it)
        startActivity(moveIntent)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("isLinear", isLinear)
    }

}