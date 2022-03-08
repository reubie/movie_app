package com.example.movieapplication.repository

import com.example.movieapplication.database.dao.FavoriteDao
import com.example.movieapplication.models.Favorite

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
