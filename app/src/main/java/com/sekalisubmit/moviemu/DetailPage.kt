package com.sekalisubmit.moviemu

import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailPage : AppCompatActivity() {
    companion object {
        const val EXTRA_MOVIE = "movie_data"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_page)

        val detailImage: ImageView = findViewById(R.id.detail_image)
        val detailTitle: TextView = findViewById(R.id.detail_title)
        val detailOverview: TextView = findViewById(R.id.detail_overview)

        val movie = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Movie>(EXTRA_MOVIE, Movie::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Movie>(EXTRA_MOVIE)
        }

        if (movie != null) {
            detailImage.setImageResource(movie.photo)
            detailTitle.text = movie.title.toString()
            detailOverview.text = movie.overview.toString()
        }
    }
}