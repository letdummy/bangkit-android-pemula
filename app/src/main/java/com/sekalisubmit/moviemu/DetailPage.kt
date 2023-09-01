package com.sekalisubmit.moviemu

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Button
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

        val btnShare: Button = findViewById(R.id.action_share)

        val movie = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Movie>(EXTRA_MOVIE, Movie::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Movie>(EXTRA_MOVIE)
        }

        if (movie != null) {
            detailImage.setImageResource(movie.image)
            detailTitle.text = movie.title.toString()
            detailOverview.text = movie.overview.toString()
        }

        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            detailOverview.setPadding(500, 0, 500, 100)
        }

        btnShare.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "image/png"

            val packageName = packageName
            val imageUri: Uri = Uri.parse("android.resource://$packageName/drawable/"+movie?.image)
            intent.putExtra(Intent.EXTRA_STREAM, imageUri)

            startActivity(Intent.createChooser(intent, "Share Image"))
        }
    }
}