package com.example.filmimdbappkotlin.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filmimdbappkotlin.model.MovieItem
import com.example.filmimdbappkotlin.network.RetrofitClient
import com.example.filmimdbappkotlin.util.Constants
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    val movieList : MutableLiveData<List<MovieItem?>?> = MutableLiveData()
    val loading = MutableLiveData(false)
    val errorMessage : MutableLiveData<String?> = MutableLiveData()


    fun getMovieList(){
        loading.value = true

        viewModelScope.launch {
          try {
              val response = RetrofitClient.getClient().getMovieList(token = Constants.BEARER_TOKEN)

              if (response.isSuccessful) {
                  movieList.postValue(response.body()?.movieItems)

              }else {
                  if (response.message().isNullOrEmpty()){
                      errorMessage.value = "Hata!"
                  } else {
                      errorMessage.value = response.message()

                  }
              }

          } catch (e: Exception) {
                //buraya ekleme yapılacak.
          }
            finally {
                loading.value = false
            }
        }
    }
}