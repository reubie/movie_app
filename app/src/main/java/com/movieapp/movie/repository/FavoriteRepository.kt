package com.movieapp.movie.repository

import com.movieapp.movie.database.dao.*
import com.movieapp.movie.models.*

class FavoriteRepository(
    private val favoriteDao: FavoriteDao
) {
    val favorites = favoriteDao.getFavoriteList()

    suspend fun addFavorite(favorite: Favorite){
        favoriteDao.addFavorite(favorite)
    }

    suspend fun deleteFavorite(favorite: Favorite){
        favoriteDao.deleteFavorite(favorite)
    }

    fun isFavorite(favorite: Favorite): Int{
        return favoriteDao.isFavorite(favorite.favId)
    }

}
