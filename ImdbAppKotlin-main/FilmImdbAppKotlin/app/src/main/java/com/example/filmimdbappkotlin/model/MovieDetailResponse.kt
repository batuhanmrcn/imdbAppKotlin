package com.example.filmimdbappkotlin.model

data class MovieDetailResponse(
    val backdrop_path: String,
    val overview: String,
    val production_companies: List<ProductionCompany>,
    val spoken_languages: List<SpokenLanguage>,
    val title: String,
    val vote_average: Double,

)