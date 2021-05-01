package com.ulanapp.skywebprotestapp.presentation.images

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ulanapp.skywebprotestapp.databinding.RecyclerItemImageBinding
import com.ulanapp.skywebprotestapp.domain.model.ImagesResponse

class ImagesAdapter (
    private var data: List<ImagesResponse>
) : RecyclerView.Adapter<ImagesAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecyclerItemImageBinding.inflate(inflater, parent, false)
        return ImageViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imageResponse = data[position]
        holder.binding?.image = imageResponse
    }

    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: RecyclerItemImageBinding? = DataBindingUtil.bind<RecyclerItemImageBinding>(itemView)
    }
}