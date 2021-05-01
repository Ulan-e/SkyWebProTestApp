package com.ulanapp.skywebprotestapp.presentation.images

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ulanapp.skywebprotestapp.databinding.FragmentImagesBinding
import com.ulanapp.skywebprotestapp.domain.model.ImagesResponse
import com.ulanapp.skywebprotestapp.domain.usecase.GetImagesUseCase
import com.ulanapp.skywebprotestapp.presentation.base.BaseFragment
import com.ulanapp.skywebprotestapp.presentation.main.MainActivity
import kotlinx.android.synthetic.main.fragment_images.*
import javax.inject.Inject

class ImagesFragment : BaseFragment() {

    @Inject
    lateinit var getImagesUseCase: GetImagesUseCase

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

        (activity as MainActivity).supportActionBar?.title = "Картинки"

        model = ViewModelProvider(
            this,
            ImagesViewModelFactory(getImagesUseCase)
        ).get(ImagesViewModel::class.java)
        binding.model = this.model

        model.getAllImages(1, 100)
        model.data.observe(activity!!, Observer {
            setUpAdapter(it)
        })

/*        model.loadingProgress.observe(activity!!, Observer { progress ->
            progress_bar.visibility = if (progress == true)
                View.VISIBLE
            else
                View.GONE
        })*/

        model.errorMessage.observe(activity!!, Observer { errorMessage ->
            if (!errorMessage.isNullOrEmpty()) {
                Log.d("iamuli", errorMessage.toString())
                Toast.makeText(activity!!, "Error loading data", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setUpAdapter(list: List<ImagesResponse>?) {
        val adapter = list?.let { ImagesAdapter(it) }
        binding.recyclerViewImages.adapter = adapter
        adapter?.notifyDataSetChanged()
    }
}