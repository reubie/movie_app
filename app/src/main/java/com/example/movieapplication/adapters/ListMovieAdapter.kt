package com.example.movieapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.squareup.picasso.Picasso
import com.example.movieapplication.models.Favorite
import com.example.movieapplication.models.MoviesWithGenres
import com.example.movieapplication.utils.EXTRA_IMG_URL_W780
import com.example.movieapplication.utils.EXTRA_MEDIA_TYPE_MOVIE
import com.example.movieapplication.utils.changeFavoriteIcon
import com.example.movieapplication.utils.favBtnClick
import com.movieapp.movie.databinding.ListMovieItemBinding
import kotlinx.coroutines.DelicateCoroutinesApi

class ListMovieAdapter(private val moviesWithGenres: List<MoviesWithGenres>,
                       val callback: (MoviesWithGenres) -> Unit) : RecyclerView.Adapter<ListMovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListMovieItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    @DelicateCoroutinesApi
    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        with(moviesWithGenres[position]) {
            val posterURL = EXTRA_IMG_URL_W780 + this.movie.posterPath
            Picasso.get().load(posterURL).into(holder.binding.ivListMovie)
            holder.binding.textListItemScore.text = this.movie.voteAverage.toString()
            val favorite = Favorite(this.movie.movieId, this.movie.posterPath, this.movie.title, EXTRA_MEDIA_TYPE_MOVIE)
            val favBtn = holder.binding.ibFavorite
            changeFavoriteIcon(favorite, favBtn)
            favBtn.setOnClickListener{ favBtnClick(favorite, favBtn) }

            holder.binding.root.setOnClickListener {
                callback(this)
            }
        }

    override fun getItemCount(): Int = moviesWithGenres.size

    class ViewHolder(val binding: ListMovieItemBinding) : RecyclerView.ViewHolder(binding.root)

}