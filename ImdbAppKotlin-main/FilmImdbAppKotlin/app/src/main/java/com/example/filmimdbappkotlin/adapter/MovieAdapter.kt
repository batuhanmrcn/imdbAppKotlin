package com.example.filmimdbappkotlin.adapter

import android.graphics.Movie
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.filmimdbappkotlin.databinding.ItemRowBinding
import com.example.filmimdbappkotlin.model.MovieItem
import com.example.filmimdbappkotlin.util.loadCircleImage


interface MovieClickListener{
    fun onMovieClicked(movieId : Int?)
}

class MovieAdapter(private val movieList : List<MovieItem?>,private val movieClickListener: MovieClickListener ) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movieList[position]
        holder.binding.textViewMovieTitle.text = movie?.title
        holder.binding.textViewMovieOverview.text = movie?.overview
        holder.binding.textViewMovieVote.text = movie?.voteAverage.toString()
        holder.binding.imageViewMovie.loadCircleImage(movie?.posterPath!!)
        holder.binding.root.setOnClickListener {
            movieClickListener.onMovieClicked(movieId = movie?.id)
        }
    }
}