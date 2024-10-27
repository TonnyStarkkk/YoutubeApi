package com.example.youtubeapi.data.model.thumbnails

import com.google.gson.annotations.SerializedName

data class MaxRes(
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int,
    @SerializedName("height")
    val height: Int
)