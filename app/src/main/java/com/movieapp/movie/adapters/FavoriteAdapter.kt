package com.movieapp.movie.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.movieapp.movie.MoviesApplication
import com.movieapp.movie.R
import com.movieapp.movie.databinding.FavoriteItemBinding
import com.movieapp.movie.models.Favorite
import com.movieapp.movie.utils.EXTRA_IMG_URL_W780
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FavoriteAdapter (private val favorites: List<Favorite>,
                       val callback: (Favorite) -> Unit) : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FavoriteItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    @DelicateCoroutinesApi
    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        with(favorites[position]) {
            if(this.favImage != null){
                val posterURL = EXTRA_IMG_URL_W780 + this.favImage
                Picasso.get().load(posterURL).into(holder.binding.ivFavorite)
            }

            holder.binding.tvFavTitle.text = this.favTitle
            holder.binding.ibFavorite.setImageResource(R.drawable.red_favorite)
            holder.binding.ibFavorite.setOnClickListener{
                GlobalScope.launch { MoviesApplication.favoriteRepository.deleteFavorite(this@with) }
            }
            holder.binding.root.setOnClickListener {
                callback(this)
            }
        }

    override fun getItemCount(): Int = favorites.size

    class ViewHolder(val binding: FavoriteItemBinding) : RecyclerView.ViewHolder(binding.root)

}