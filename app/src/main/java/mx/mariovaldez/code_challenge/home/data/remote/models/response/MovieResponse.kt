package mx.mariovaldez.code_challenge.home.data.remote.models.response

import com.google.gson.annotations.SerializedName

internal data class MovieResponse(

    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<Movie>,
)
