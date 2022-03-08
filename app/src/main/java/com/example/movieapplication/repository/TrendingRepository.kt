package com.example.movieapplication.repository

import com.example.movieapplication.database.dao.GenreDao
import com.example.movieapplication.database.dao.TrendingNowDao
import com.example.movieapplication.models.TrendingGenreCrossRef
import com.example.movieapplication.models.TrendingWithGenres
import com.example.movieapplication.network.TMDbApi

class TrendingRepository(
    private val genreDao: GenreDao,
    private val trendingDao: TrendingNowDao,
    private val tmDbApi: TMDbApi
) {
    val trendingWithGenres = trendingDao.getTrendingListWithGenres()

    suspend fun fetchTrendingAndGenres(page: Int = 1) {
        val genres = tmDbApi.genres().genres ?: mutableListOf()
        genreDao.addGenres(genres)
        val trendingList = tmDbApi.trendingNow(page).results
        trendingDao.addTrendingNowList(trendingList)
        trendingList.forEach { trending ->
            trending.genreIds.forEach { genreId ->
                trendingDao.addTrendingGenreCrossRef(
                    TrendingGenreCrossRef(trending.trendingNowId, genreId.toLong())
                )
            }
        }
    }

    fun findTrendingWithGenresById(id: Long, callback: (TrendingWithGenres) -> Unit){
        callback(trendingDao.getTrendingWithGenresById(id))
    }

}
