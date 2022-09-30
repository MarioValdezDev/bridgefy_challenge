package mx.mariovaldez.code_challenge.home.data.remote.models.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class VideoResult(
    @SerializedName("iso_639_1")
    val iso639_1: String,

    @SerializedName("iso_3166_1")
    val iso3166_1: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("key")
    val key: String,

    @SerializedName("site")
    val site: String,

    @SerializedName("size")
    val size: Int,

    @SerializedName("type")
    val type: String,

    @SerializedName("official")
    val official: Boolean,

    @SerializedName("published_at")
    val publishedAt: String,

    @SerializedName("id")
    val id: String,

) : Serializable
