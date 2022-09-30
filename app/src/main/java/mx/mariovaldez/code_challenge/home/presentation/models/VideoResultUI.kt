package mx.mariovaldez.code_challenge.home.presentation.models

data class VideoResultUI(
    val iso639_1: String,
    val iso3166_1: String,
    val name: String,
    val key: String,
    val site: String,
    val size: Int,
    val type: String,
    val official: Boolean,
    val publishedAt: String,
    val id: String,
)
