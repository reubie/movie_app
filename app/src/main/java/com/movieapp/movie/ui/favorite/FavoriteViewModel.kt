package com.movieapp.movie.ui.favorite

import androidx.lifecycle.ViewModel
import com.movieapp.movie.MoviesApplication

class FavoriteViewModel : ViewModel() {

    val favoriteList = MoviesApplication.favoriteRepository.favorites

}