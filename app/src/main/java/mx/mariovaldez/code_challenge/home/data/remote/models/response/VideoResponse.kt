package mx.mariovaldez.code_challenge.home.data.remote.models.response

import com.google.gson.annotations.SerializedName

data class VideoResponse(

    @SerializedName("id")
    val id: Int,

    @SerializedName("results")
    val results: List<VideoResult>,
)
