package com.example.filmimdbappkotlin.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.filmimdbappkotlin.R
import com.example.filmimdbappkotlin.databinding.FragmentDetailBinding
import com.example.filmimdbappkotlin.util.loadImage


class DetailFragment : Fragment() {
    private var _binding : FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<DetailViewModel>()

    private val args by navArgs<DetailFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentDetailBinding.inflate(inflater,container,false)
        viewModel.getMovieDetail(movieId = args.movieId)
        observeEvents()


        return binding.root



    }

    private fun observeEvents() {
        viewModel.loading.observe(viewLifecycleOwner) {

        }
        viewModel.movieResponse.observe(viewLifecycleOwner) {movie ->
            binding.imageViewDetail.loadImage(movie.backdrop_path)
            binding.textViewDetailStudio.text = movie.production_companies.first()?.name
            binding.textViewDetailLanguage.text = movie.spoken_languages.first()?.english_name
            binding.textViewDetailOverview.text = movie.overview
            
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}