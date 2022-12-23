package com.example.album.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.album.databinding.FragmentOverviewSecondBinding

class OverviewFragmentSecond : Fragment() {

    private val viewModel: OverviewViewModelSecond by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentOverviewSecondBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        binding.photosGrid.adapter = AlbumGridAdapter()

        return binding.root
    }
}
