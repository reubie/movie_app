package com.movieapp.movie.ui.list

import androidx.lifecycle.ViewModel
import com.movieapp.movie.MoviesApplication

class ListViewModel : ViewModel() {

    val popularMovies = MoviesApplication.movieRepository.moviesWithGenres
    val popularTvs = MoviesApplication.tvRepository.tvsWithGenres
    val trendingNow = MoviesApplication.trendingRepository.trendingWithGenres

}