package mx.mariovaldez.code_challenge.home.presentation.models

import java.io.Serializable

data class MovieUI(
    val adult: Boolean,
    val backdropPath: String,
    val genreIds: List<Int>,
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Float,
    val posterPath: String,
    val releaseDate: String,
    val video: Boolean,
    val voteAverage: Float,
    val voteCount: Int,
) : Serializable
