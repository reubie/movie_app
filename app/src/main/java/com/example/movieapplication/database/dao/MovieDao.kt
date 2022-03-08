package com.example.movieapplication.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.movieapplication.models.Movie
import com.example.movieapplication.models.MovieGenreCrossRef
import com.example.movieapplication.models.MoviesWithGenres

@Dao
interface MovieDao {

    //Movie
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovie(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovies(movies: List<Movie>)

    @Transaction
    @Query("SELECT * FROM Movie")
    fun getMovies(): LiveData<List<Movie>>

    //Movie with genres
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovieGenreCrossRef(movieGenreCrossRef: MovieGenreCrossRef)

    @Transaction
    @Query("SELECT * FROM Movie")
    fun getMoviesWithGenres(): LiveData<List<MoviesWithGenres>>

    @Query("SELECT * FROM Movie WHERE movieId = :id")
    fun getMovieWithGenresById(id: Long): MoviesWithGenres

}