package com.example.movieapplication.utils

import android.widget.ImageButton
import com.example.movieapplication.MoviesApplication
import com.example.movieapplication.models.Favorite
import com.example.movieapplication.models.Genre
import com.movieapp.movie.R
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

fun getGenresStr(genres: List<Genre>): String{
    val genresStr: StringBuilder = StringBuilder()
    for (g in genres){
        genresStr.append(" ${g.name}")
        if(genres.lastIndexOf(g) < genres.size - 1)
            genresStr.append(" |")
    }
    return genresStr.toString()
}

@DelicateCoroutinesApi
fun favBtnClick(favorite: Favorite, favBtn: ImageButton){
    GlobalScope.launch {
        if(MoviesApplication.favoriteRepository.isFavorite(favorite) != 1){
            favBtn.setImageResource(R.drawable.red_favorite)
            MoviesApplication.favoriteRepository.addFavorite(favorite)
        }else{
            favBtn.setImageResource(R.drawable.favorite)
            MoviesApplication.favoriteRepository.deleteFavorite(favorite)
        }
    }
}

@DelicateCoroutinesApi
fun changeFavoriteIcon(favorite: Favorite, favBtn: ImageButton){
    GlobalScope.launch {
        if (MoviesApplication.favoriteRepository.isFavorite(favorite) == 1) {
            favBtn.setImageResource(R.drawable.red_favorite)
        } else {
            favBtn.setImageResource(R.drawable.favorite)
        }
    }
}

