package com.example.youtubeapi.ui.fragments.playlists

import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtubeapi.data.base.BaseFragment
import com.example.youtubeapi.databinding.FragmentPlayListsBinding
import com.example.youtubeapi.utils.Resource
import org.koin.androidx.viewmodel.ext.android.viewModel


class PlaylistsFragment : BaseFragment<FragmentPlayListsBinding>(FragmentPlayListsBinding::inflate) {

    private val viewModel: PlaylistsViewModel by viewModel()
    private val adapter: PlaylistsAdapter by lazy {
        PlaylistsAdapter()
    }

    override fun setupViews() {
        super.setupViews()
        setupRecyclerView()
    }

    override fun setupObservers() {
        viewModel.getPlaylists().stateHandler(
            success = {data ->
                adapter.submitList(data)
            },
            state = { state ->
                binding.pbLoading.isVisible = state is Resource.Loading
            }
        )
    }

    private fun setupRecyclerView()= with(binding.rvPlaylists) {
        layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }
}