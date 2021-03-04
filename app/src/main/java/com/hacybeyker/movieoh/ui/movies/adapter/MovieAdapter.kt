package com.hacybeyker.movieoh.ui.movies.adapter;

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.hacybeyker.entities.Movie
import com.hacybeyker.movieoh.R
import com.hacybeyker.movieoh.databinding.ItemMovieBinding

class MovieAdapter(private val onItemSelectedListener: OnItemSelectedListener) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolderViewHolder>() {

    private val items = arrayListOf<Movie>()

    fun addData(data: List<Movie>) {
        this.items.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolderViewHolder {
        return MovieViewHolderViewHolder.from(parent, onItemSelectedListener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MovieViewHolderViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class MovieViewHolderViewHolder(
        private val binding: ItemMovieBinding,
        private val onItemSelectedListener: OnItemSelectedListener
    ) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(
                parent: ViewGroup,
                onItemSelectedListener: OnItemSelectedListener
            ): MovieViewHolderViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemMovieBinding.inflate(layoutInflater, parent, false)
                return MovieViewHolderViewHolder(binding, onItemSelectedListener)
            }
        }

        fun bind(item: Movie) {
            binding.movie = item
            binding.onItemSelected = onItemSelectedListener
            binding.imageViewTransition =
                itemView.findViewById<AppCompatImageView>(R.id.imageMoviePoster)
            binding.executePendingBindings()
        }
    }

    interface OnItemSelectedListener {
        fun onItemSelected(item: Movie, view: View?)
    }
}