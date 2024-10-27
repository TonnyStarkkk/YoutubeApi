package com.example.youtubeapi.data.repository

import androidx.lifecycle.LiveData
import com.example.youtubeapi.data.api.ApiService
import com.example.youtubeapi.data.base.BaseRepository
import com.example.youtubeapi.data.model.BaseResponse
import com.example.youtubeapi.data.model.Item
import com.example.youtubeapi.utils.Constants
import com.example.youtubeapi.utils.Resource
import retrofit2.Response
import com.example.youtubeapi.BuildConfig


class Repository(
    private val api: ApiService
) : BaseRepository() {

    fun getPlaylists(): LiveData<Resource<List<Item>>> = sendRequest {
        val response: Response<BaseResponse> = api.getPlaylists(
            channelId = Constants.CHANNEL_ID,
            part = Constants.PART,
            maxResults = Constants.MAX_RESULTS,
            apiKey = BuildConfig.API_KEY
        )
        if (response.isSuccessful) {
            Response.success(response.body()?.items ?: emptyList())
        } else {
            Response.error(response.errorBody()!!, response.raw())
        }
    }

    companion object {
        const val ARG_ERROR_MESSAGE = "Unknown error message"
    }
}