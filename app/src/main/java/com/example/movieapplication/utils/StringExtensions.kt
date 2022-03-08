package com.example.movieapplication.utils

fun CharSequence.isEmailValid(): Boolean{
    return this.length >= 6 && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}