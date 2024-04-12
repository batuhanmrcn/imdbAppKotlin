package com.example.filmimdbappkotlin.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.filmimdbappkotlin.R
import com.example.filmimdbappkotlin.adapter.MovieAdapter
import com.example.filmimdbappkotlin.adapter.MovieClickListener
import com.example.filmimdbappkotlin.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewmodel by viewModels<HomeViewModel>()

    private lateinit var adapter : MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater,container,false)

        viewmodel.getMovieList()
        observeLiveData()



        return binding.root
    }
    fun observeLiveData(){
        viewmodel.errorMessage.observe(viewLifecycleOwner){
            binding.textViewHomeError.text = it
            binding.textViewHomeError.isVisible = true
        }
        viewmodel.loading.observe(viewLifecycleOwner) {
            binding.progressBar.isVisible = true
        }
        viewmodel.movieList.observe(viewLifecycleOwner) { list ->
            if (list.isNullOrEmpty()) {
                binding.textViewHomeError.text = "Film Bulunamadı!"
                binding.textViewHomeError.isVisible = true

            } else {
                adapter = MovieAdapter(list,object : MovieClickListener{
                    override fun onMovieClicked(movieId: Int?) {
                        movieId?.let {
                            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(it)
                            findNavController().navigate(action)
                        }
                    }
                })
                binding.homeRecyclerView.adapter = adapter
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}