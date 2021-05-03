package com.ulanapp.skywebprotestapp.presentation.images

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.ulanapp.skywebprotestapp.R
import com.ulanapp.skywebprotestapp.databinding.FragmentImagesBinding
import com.ulanapp.skywebprotestapp.domain.usecase.GetImagesUseCase
import com.ulanapp.skywebprotestapp.presentation.base.BaseFragment
import com.ulanapp.skywebprotestapp.presentation.images.paging.ImagesPagedListAdapter
import com.ulanapp.skywebprotestapp.presentation.images.paging.State
import com.ulanapp.skywebprotestapp.presentation.main.MainActivity
import kotlinx.android.synthetic.main.fragment_images.*
import javax.inject.Inject

class ImagesFragment : BaseFragment() {

    @Inject
    lateinit var imagesUseCase: GetImagesUseCase

    @Inject
    lateinit var adapter: ImagesPagedListAdapter

    private lateinit var binding: FragmentImagesBinding
    private lateinit var model: ImagesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImagesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).supportActionBar?.title = resources.getString(R.string.images)

        model = ViewModelProvider(this, ImagesViewModelFactory(imagesUseCase))
            .get(ImagesViewModel::class.java)
        binding.model = this.model

        initAdapter()
        initState()
    }

    // инициализация адаптера
    private fun initAdapter() {
        recycler_view_images.adapter = adapter
        model.imagesList.observe(this, {
            adapter.submitList(it)
        })
    }

    // инициализация состояния
    private fun initState() {
        model.getState().observe(this, { state ->
            progress_bar.visibility =
                if (model.listIsEmpty() && state == State.LOADING) View.VISIBLE
                else View.GONE
            if (!model.listIsEmpty()) {
                adapter.changeState(state ?: State.SUCCESS)
            }
        })
    }
}