package com.example.youtubeapi.data.model.thumbnails

import com.google.gson.annotations.SerializedName

data class Thumbnails(
    @SerializedName("default")
    val default: Default,
    @SerializedName("medium")
    val medium: Medium,
    @SerializedName("high")
    val high: High,
    @SerializedName("standard")
    val standard: Standard,
    @SerializedName("maxRes")
    val maxRes: MaxRes
)