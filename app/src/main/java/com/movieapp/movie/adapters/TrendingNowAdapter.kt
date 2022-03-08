package com.movieapp.movie.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.movieapp.movie.databinding.MovieItemBinding
import com.movieapp.movie.models.Favorite
import com.movieapp.movie.models.TrendingWithGenres
import com.movieapp.movie.utils.*
import kotlinx.coroutines.DelicateCoroutinesApi

class TrendingNowAdapter (private val trendingWithGenres: List<TrendingWithGenres>,
                             val callback: (TrendingWithGenres) -> Unit) : RecyclerView.Adapter<TrendingNowAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            MovieItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    @DelicateCoroutinesApi
    override fun onBindViewHolder(holder: TrendingNowAdapter.ViewHolder, position: Int) =
        with(trendingWithGenres[position]) {
            val posterURL = EXTRA_IMG_URL_W500 + this.trendingNow.posterPath
            Picasso.get().load(posterURL).into(holder.binding.ivMovie)
            holder.binding.textItemScore.text = this.trendingNow.voteAverage.toString()

            val title = if(this.trendingNow.mediaType == EXTRA_MEDIA_TYPE_MOVIE)
                this.trendingNow.title.toString()
            else
                this.trendingNow.name.toString()

            val favorite = Favorite(this.trendingNow.trendingNowId, this.trendingNow.posterPath,
                                                title, EXTRA_MEDIA_TYPE_TRENDING)
            val favBtn = holder.binding.ibFavorite
            changeFavoriteIcon(favorite, favBtn)
            favBtn.setOnClickListener{ favBtnClick(favorite, favBtn) }

            holder.binding.root.setOnClickListener {
                callback(this)
            }
        }

    override fun getItemCount(): Int = trendingWithGenres.size

    class ViewHolder(val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root)

}