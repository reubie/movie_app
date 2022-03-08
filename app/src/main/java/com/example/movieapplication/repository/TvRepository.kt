package com.example.movieapplication.repository

import com.example.movieapplication.database.dao.GenreDao
import com.example.movieapplication.database.dao.TvDao
import com.example.movieapplication.models.TvGenreCrossRef
import com.example.movieapplication.models.TvWithGenres
import com.example.movieapplication.network.TMDbApi

class TvRepository(
    private val genreDao: GenreDao,
    private val tvDao: TvDao,
    private val tmDbApi: TMDbApi
) {
    val tvsWithGenres = tvDao.getTvsWithGenres()

    suspend fun fetchTvsAndGenres(page: Int = 1) {
        val genres = tmDbApi.genres().genres ?: mutableListOf()
        genreDao.addGenres(genres)
        val tvs = tmDbApi.popularTvs(page = page).results
        tvDao.addTvs(tvs)
        tvs.forEach { tv ->
            tv.genreIds.forEach { genreId ->
                tvDao.addTvGenreCrossRef(
                    TvGenreCrossRef(tv.tvId, genreId.toLong())
                )
            }
        }
    }

    fun findTvWithGenresById(id: Long, callback: (TvWithGenres) -> Unit){
        callback(tvDao.getTvWithGenresById(id))
    }

}
