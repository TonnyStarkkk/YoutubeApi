package com.example.youtubeapi.data.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.viewbinding.ViewBinding
import com.example.youtubeapi.utils.Resource


typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VB : ViewBinding>(
    private val inflate: Inflate<VB>
) : Fragment() {

    private var _binding: VB? = null
    protected val binding: VB
        get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupViews()
        setupClickListeners()
    }

    protected open fun setupClickListeners() {}

    protected open fun setupObservers() {}

    protected open fun setupViews() {}

    protected fun <T> LiveData<Resource<T>>.stateHandler(
        success: (data: T) -> Unit,
        state: (Resource<T>) -> Unit = {}
    ) {
        observe(viewLifecycleOwner) { result ->
            state(result)
            when (result) {
                is Resource.Error -> showToast(result.message)
                is Resource.Loading -> Resource.Loading<T>()
                is Resource.Success -> success(result.data)
            }
        }
    }

    private fun showToast(message: String?) {
        Toast.makeText(requireContext(), message ?: "Unknown Error", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}