package com.example.youtubeapi.ui.fragments.playlists

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.youtubeapi.data.model.Item
import com.example.youtubeapi.data.repository.Repository
import com.example.youtubeapi.utils.Resource

class PlaylistsViewModel(
    private val repository: Repository
) : ViewModel() {
    fun getPlaylists(): LiveData<Resource<List<Item>>> = repository.getPlaylists()
}