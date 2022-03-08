package com.example.movieapplication.ui.list

import androidx.lifecycle.ViewModel
import com.example.movieapplication.MoviesApplication

class ListViewModel : ViewModel() {

    val popularMovies = MoviesApplication.movieRepository.moviesWithGenres
    val popularTvs = MoviesApplication.tvRepository.tvsWithGenres
    val trendingNow = MoviesApplication.trendingRepository.trendingWithGenres

}