package com.example.movieapplication

import android.app.Application
import android.net.ConnectivityManager
import com.example.movieapplication.database.TMDbDatabase
import com.example.movieapplication.network.NetworkStatusChecker
import com.example.movieapplication.network.TMDbApi
import com.example.movieapplication.repository.FavoriteRepository
import com.example.movieapplication.repository.MovieRepository
import com.example.movieapplication.repository.TrendingRepository
import com.example.movieapplication.repository.TvRepository

class MoviesApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        private lateinit var instance: MoviesApplication

        private val db: TMDbDatabase by lazy {
            TMDbDatabase.create(instance)
        }

        val movieRepository: MovieRepository by lazy {
            MovieRepository(db.movieDao(),
                            db.genreDao(),
                            TMDbApi.create())
        }

        val tvRepository: TvRepository by lazy {
            TvRepository(
                db.genreDao(),
                db.tvDao(),
                TMDbApi.create())
        }

        val trendingRepository: TrendingRepository by lazy {
            TrendingRepository(
                db.genreDao(),
                db.trendingDao(),
                TMDbApi.create())
        }

        val favoriteRepository: FavoriteRepository by lazy {
            FavoriteRepository(
                db.favoriteDao())
        }

        val networkStatusChecker: NetworkStatusChecker by lazy{
            NetworkStatusChecker(instance.getSystemService(ConnectivityManager::class.java))
        }
    }

}

