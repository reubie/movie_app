package com.example.movieapplication.ui.favorite

import androidx.lifecycle.ViewModel
import com.example.movieapplication.MoviesApplication

class FavoriteViewModel : ViewModel() {

    val favoriteList = MoviesApplication.favoriteRepository.favorites

}