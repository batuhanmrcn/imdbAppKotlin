package com.example.filmimdbappkotlin.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filmimdbappkotlin.model.MovieDetailResponse
import com.example.filmimdbappkotlin.network.RetrofitClient
import com.example.filmimdbappkotlin.util.Constants
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {

    val movieResponse : MutableLiveData<MovieDetailResponse> = MutableLiveData()
    val loading = MutableLiveData(false)
    val errorMessage : MutableLiveData<String?> = MutableLiveData()

    fun getMovieDetail(movieId : Int) {
        loading.value = true

        viewModelScope.launch {
            try {
                val response = RetrofitClient.getClient().getMovieDetail(movieId = movieId.toString(), token = Constants.BEARER_TOKEN)

                if (response.isSuccessful) {
                    movieResponse.postValue(response.body())
                } else {
                    if (response.message().isNullOrEmpty()) {
                        errorMessage.value = "Hata!"
                    } else {
                        errorMessage.value = response.message()
                    }
                }
            } catch (e : Exception) {
                errorMessage.value = e.message
            } finally {
                loading.value = false

            }
        }
    }
}