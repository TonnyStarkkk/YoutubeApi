package com.example.youtubeapi.data.model

import com.example.youtubeapi.data.model.thumbnails.Thumbnails
import com.google.gson.annotations.SerializedName

data class Snippet(
    @SerializedName("publishedAt")
    val publishedAt: String,
    @SerializedName("channelId")
    val channelId: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("thumbnails")
    val thumbnails: Thumbnails,
    @SerializedName("channelTitle")
    val channelTitle: String,
)