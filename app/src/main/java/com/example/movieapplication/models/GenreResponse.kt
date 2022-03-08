package com.example.movieapplication.models

import com.example.movieapplication.models.Genre
import com.google.gson.annotations.SerializedName

data class GenreResponse(
    val genres: List<Genre>?,
    @SerializedName("status_message")
    val statusMessage: String?,
    @SerializedName("status_code")
    val statusCode: Int?,
    val success: Boolean?
)