package com.example.youtubeapi.data.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.youtubeapi.data.repository.Repository.Companion.ARG_ERROR_MESSAGE
import com.example.youtubeapi.utils.Resource
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

abstract class BaseRepository {

    fun <T> sendRequest(
        requestFun: suspend () -> Response<T>
    ):LiveData<Resource<T>> = liveData(Dispatchers.IO) {
        try {
            emit(Resource.Loading())
            val response = requestFun()
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(Resource.Success(it))
                } ?: run {
                    emit(Resource.Error("Could not send request"))
                }
            } else {
                emit(Resource.Error("Error: ${response.code()} ${response.message()}"))
            }
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: ARG_ERROR_MESSAGE))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: ARG_ERROR_MESSAGE))
        }
    }
}