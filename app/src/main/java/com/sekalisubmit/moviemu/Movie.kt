package com.sekalisubmit.moviemu

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val image: Int,
    val title: String,
    val overview: String
) : Parcelable