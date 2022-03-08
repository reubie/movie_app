package com.example.movieapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.example.movieapplication.models.Favorite
import com.example.movieapplication.models.TvWithGenres
import com.example.movieapplication.utils.EXTRA_IMG_URL_W780
import com.example.movieapplication.utils.EXTRA_MEDIA_TYPE_TV
import com.example.movieapplication.utils.changeFavoriteIcon
import com.example.movieapplication.utils.favBtnClick
import com.movieapp.movie.databinding.ListMovieItemBinding
import kotlinx.coroutines.DelicateCoroutinesApi

class ListTvAdapter (private val tvsWithGenres: List<TvWithGenres>,
                     val callback: (TvWithGenres) -> Unit) : RecyclerView.Adapter<ListTvAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListMovieItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    @DelicateCoroutinesApi
    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        with(tvsWithGenres[position]) {
            val posterURL = EXTRA_IMG_URL_W780 + this.tv.posterPath
            Picasso.get().load(posterURL).into(holder.binding.ivListMovie)
            holder.binding.textListItemScore.text = this.tv.voteAverage.toString()
            val favorite = Favorite(this.tv.tvId, this.tv.posterPath, this.tv.name, EXTRA_MEDIA_TYPE_TV)
            val favBtn = holder.binding.ibFavorite
            changeFavoriteIcon(favorite, favBtn)
            favBtn.setOnClickListener{ favBtnClick(favorite, favBtn) }

            holder.binding.root.setOnClickListener {
                callback(this)
            }
        }

    override fun getItemCount(): Int = tvsWithGenres.size

    class ViewHolder(val binding: ListMovieItemBinding) : RecyclerView.ViewHolder(binding.root)

}