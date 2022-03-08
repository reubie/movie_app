package com.example.movieapplication.repository

import com.example.movieapplication.database.dao.GenreDao
import com.example.movieapplication.database.dao.MovieDao
import com.example.movieapplication.models.MovieGenreCrossRef
import com.example.movieapplication.models.MoviesWithGenres
import com.example.movieapplication.network.TMDbApi

class MovieRepository(
    private val movieDao: MovieDao,
    private val genreDao: GenreDao,
    private val tmDbApi: TMDbApi
) {
    val moviesWithGenres = movieDao.getMoviesWithGenres()

    suspend fun fetchMoviesAndGenres(page:Int = 1) {
        val genres = tmDbApi.genres().genres ?: mutableListOf()
        genreDao.addGenres(genres)
        val movies = tmDbApi.popularMovies(page).results
        movieDao.addMovies(movies)
        movies.forEach { movie ->
            movie.genreIds.forEach { genreId ->
                movieDao.addMovieGenreCrossRef(
                    MovieGenreCrossRef(movie.movieId, genreId.toLong())
                )
            }
        }
    }

    fun findMovieWithGenresById(id: Long, callback: (MoviesWithGenres) -> Unit){
        callback(movieDao.getMovieWithGenresById(id))
    }

}
