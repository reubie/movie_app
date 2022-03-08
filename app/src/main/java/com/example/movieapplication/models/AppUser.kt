package com.example.movieapplication.models

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class AppUser ( val userId: String? = null, val email: String? = null)